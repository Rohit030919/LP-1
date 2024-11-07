import java.util.*;

public class Optimal{
public static void main(String args[]){
		int pages[] = {0,1,2,0,3,4,5,0,4,2,5,3,1,4,2,3,4,1,0,3,2,4,2,5,1,4,2,3,1,1,0};
		int frame[] = {-1,-1,-1,-1};
		int pageHit=0, pageFault = 0;
		for(int i=0;i<pages.length;i++){
			int x = pages[i];
			boolean found=false;
			for(int j=0; j<frame.length;j++){
				if(x==frame[j]){
					found = true;
					pageHit++;
					break;
				}
			}
			if(!found){
				pageFault++;
				int cnt[] = new int[4];
				for(int j=0;j<cnt.length;j++){
					cnt[j] = pages.length;
					for(int k=i+1; k<pages.length;k++){
						if(frame[j]==pages[k]){
							cnt[j]=pages[k];
						}
					}
				}
				int max = 0;
				for(int a=1;a<cnt.length;a++){
					if(cnt[a]>cnt[max]){
						max = a;
					}
				}
				frame[max] = x;
			}
			System.out.println("Page Reference:"+x);
			for(int a=0;a<frame.length;a++){
				System.out.print(" "+frame[a]);
			}
			System.out.println();
		}
		System.out.println("Page Hit"+pageHit);
		System.out.println("Page Fault" +pageFault);
		System.out.println("Ratio of Pagehit"+((double)pageHit/pages.length)*100);
		System.out.println("Ratio of PageFault"+((double)pageFault/pages.length)*100);
	}
}
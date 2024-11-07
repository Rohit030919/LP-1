public class FIFO{
public static void main(String args[]){
		int pages[] = {7,0,1,2,0,3,7,4,1,7,0,1,0,1,3,2,4,0,1,7};
		int frame[] = {-1,-1,-1};
		int rep = 0;
		int pageHit=0, pageFault =0;
		int i;
		for(i=0; i<pages.length; i++){
			int x = pages[i];
			if(x != frame[0] && x != frame[1] && x != frame[2]){
				pageFault++;
				frame[rep] = x;
				rep=(rep+1)%3;
			}else if(x==frame[1]||x==frame[2]||x==frame[3]){
				pageHit++;
			}
			System.out.println("Reference Page:" +x);
			for(int j=0;j<frame.length;j++){
				System.out.print(" "+frame[j]);
			}
			System.out.println();
		}
		System.out.println("Page Hit::"+pageHit);
		System.out.println("Page Fault::"+pageFault);
	}
}
public class LRU{
public static void main(String args[]){
		int pages[] = {1,0,2,2,4,5,0,2,2,1,3,2,1,0,2,4,4,5,3,2,2,0,1};
		int frame[] = {-1,-1,-1,-1};
		int usageOrder[] = {-1,-1,-1,-1};
		int pageFault = 0, pageHit = 0;
		for(int i = 0;i<pages.length;i++){
			int x = pages[i];
			boolean found = false;
			for(int j=0;j<frame.length;j++){
				if(x==frame[j]){
					pageHit++;
					usageOrder[j] = i;
					found = true;
				}
			}
			if(!found){
				int lruIndex = 0;
				int oldestUsage = Integer.MAX_VALUE;
				pageFault++;
				for(int j=0;j<frame.length;j++){
					if(frame[j]==-1){
						lruIndex = j;
						break;
					}
					if(usageOrder[j]<oldestUsage){
						oldestUsage=usageOrder[j];
						lruIndex = j;
					}
				}
				frame[lruIndex] = x;
				usageOrder[lruIndex]=i;
			}
			System.out.println("PageReference:" +x);
				for(int j=0;j<frame.length;j++){
					System.out.print(" " +frame[j]);
				}
				System.out.println();
		}
		System.out.println("PageFault:"+pageFault);
		System.out.println("PageHit"+pageHit);
	}
}
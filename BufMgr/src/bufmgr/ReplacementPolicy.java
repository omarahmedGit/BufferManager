package bufmgr;

public class ReplacementPolicy {
	private int function ;
	public ReplacementPolicy(bufmgr bufmgr, String replacementPolicy)
	{
		// fifo - MUR - LRU - love/hate
		if(replacementPolicy.equals("fifo"))
		{
			function = 1;
		} else if(replacementPolicy.equals("MUR")) {
			function = 2;
		} else if(replacementPolicy.equals("LUR")) {
			function = 3;
		} else if(replacementPolicy.equals("love/hate")) {
			function = 4;
		}
	}
	
	public void refreshQueue()
	{
		if(function==1)
		{
			
		}
		else if(function==2)
		{
			
		}
		else if(function==3)
		{
			
		}
		else if(function==4)
		{
			
		}
	}
	
	
	
	public int getPageIndex(){
		
		return 0;
	}
}

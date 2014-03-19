package bufmgr;

import global.PageId;

public class ReplacementPolicy {
	private int function , max ;
	private bufmgr bufmgr;
	
	
	public ReplacementPolicy(bufmgr bufmgr, String replacementPolicy)
	{
		// fifo - MUR - LRU - love/hate
		if(replacementPolicy.equals("fifo"))
		{
			function = 1;
		} else if(replacementPolicy.equals("MRU")) {
			function = 2;
		} else if(replacementPolicy.equals("LRU")) {
			function = 3;
		} else if(replacementPolicy.equals("love/hate")) {
			function = 4;
		}
	}
	
	public void pinPageRefresh(int frame)
	{
		if(function==1)
			mruPinRefreshe(frame);
		else if(function==2)
			lruPinRefreshe(frame);
	}
	
	public void lruPinRefreshe(int frame)
	{
		bufmgr.order[frame] = max++; 
	}
	
	public void mruPinRefreshe(int frame)
	{
		bufmgr.order[frame] = max--; 
	}
	
	public PageId getPageIndex(){
		RepalcementCandidate rep;
		while(!bufmgr.candidates.isEmpty())
		{
			rep = bufmgr.candidates.poll();
			if(bufmgr.getPinCount(rep.index)!=0) continue;
			return rep.pgid;
					
		}
		return null;
	}
}

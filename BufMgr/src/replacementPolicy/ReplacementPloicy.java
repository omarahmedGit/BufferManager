package replacementPolicy;

import bufmgr.*;

public class ReplacementPloicy {
	Policy current;
	public ReplacementPloicy(String policy, BufMgr bufmgr)
	{
		if(policy.equals("fifo"))
			current = new FIFO(bufmgr);
		else if(policy.equals("LRU"))
			current = new LRU(bufmgr);
		else
			current = new MRU(bufmgr);

		// if(policy.equals("MRU"))
		// 	current = new MRU(bufmgr);
	}



	public void pinPage(int frame)
	{
		current.pinPage(frame);
	}


	public void unpinPage(int frame)
	{
		current.unpinPage(frame);
	}

}

package replacementPolicy;

import java.util.LinkedList;

import bufmgr.BufMgr;

public class MRU implements Policy{

	private LinkedList<Integer> list;
	private int[] order;
	private BufMgr bufmgr;
	private periorty;
	
	public MRU (BufMgr BufferManager)
	{
		bufmgr = BufferManager;
		list = new LinkedList<Integer>();
		order = new int[bufmgr.getNumBufs() + 1];
		periorty = 0;
		for(int i=1;i<=bufmgr.getNumBufs();i++)
			list.add(i);

	}
	
	@Override
	public void pinPage(int frame) {
		order[frame] = ++periorty;

		if(bufmgr.getPinCount(frame)==0)
		{
			for(int i=0;i<list.size();i++)
				if(list.get(i)==frame)
				{	
					list.remove(i);
					break;
				}
		}

	}

	@Override
	public void unpinPage(int frame) {
		if(bufmgr.getPinCount()==0)
		{
			for (int i=0;i<list.size;i++) {
				if(order[list.get(i)]<order[frame])
				{
					list.add(i,frame);
					break;
				} else if(i==list.size()-1)
				{
					list.add(frame);
				}
			}
			if(list.size==0)
				list.add(frame);
		}
	}

	@Override
	public int requestPage() {
		if(list.size()==0)
			return 0;
		int frame = list.get(0);
		list.remove(0);
		return frame;
	}

}

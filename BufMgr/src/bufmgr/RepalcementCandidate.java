package bufmgr;

import global.PageId;
import diskmgr.Page;

public class RepalcementCandidate implements Comparable<RepalcementCandidate> {
	PageId pgid;
	int index;
	bufmgr bufmgr;
	
	@Override
	public int compareTo(RepalcementCandidate o) {
		return bufmgr.order[this.index]-bufmgr.order[o.index];
	}
}

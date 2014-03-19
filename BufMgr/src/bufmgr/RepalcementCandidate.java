package bufmgr;

import global.PageId;
import diskmgr.Page;

public class RepalcementCandidate implements Comparable<RepalcementCandidate> {
	PageId pgid;
	int index;
	
	
	@Override
	public int compareTo(RepalcementCandidate o) {
		return 0;
	}
}

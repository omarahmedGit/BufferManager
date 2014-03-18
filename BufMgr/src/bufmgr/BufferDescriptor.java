package bufmgr;

import global.PageId;

public class BufferDescriptor {
	
	private int pinCount;
	private PageId pageNumber;
	private boolean dirty;
	
	public BufferDescriptor(){
		pinCount = 0;
		dirty = false;
		pageNumber = new PageId();
	}
	public BufferDescriptor(PageId pgid){
		pinCount = 0;
		dirty = false;
		pageNumber = pgid;
	}
	
	public void setPageNumber(PageId pageNumber) {
		this.pageNumber = pageNumber;
	}
	public PageId getPageNumber() {
		return pageNumber;
	}
	
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public boolean isDirty() {
		return dirty;
	}
	
	
	
	public void setPinCount(int pinCount) {
		this.pinCount = pinCount;
	}
	public int getPinCount() {
		return pinCount;
	}
	/**
	 * increment/decrement the pinCount.
	 * @param plusOrMinus: 1(increment) or -1(decrement)
	 */
	public void updatePinCount(int plusOrMinus)
	{
		pinCount+=plusOrMinus;
	}
	
}

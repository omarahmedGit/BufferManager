package bufmgr;
import java.io.IOException;
import java.util.Hashtable;
import java.util.PriorityQueue;

import global.PageId;
import global.SystemDefs;
import diskmgr.DiskMgrException;
import diskmgr.FileIOException;
import diskmgr.InvalidPageNumberException;
import diskmgr.InvalidRunSizeException;
import diskmgr.Page;
	
public class bufmgr {
	
	private byte[][] buffPool;
	private Page[] pagesInThePool;
	private String replacementPolicy;
	
	private PriorityQueue<RepalcementCandidate> candidates;
	private int[] order;
	
	private BufferDescriptor[] bufferDescriptors; // Map the frame to the pages
	private Hashtable<PageId, Integer> pageToFrameMap;
	
	
	/**
	* Create the BufMgr object
	* Allocate pages (frames) for the buffer pool in main memory and
	* make the buffer manager aware that the replacement policy is
	* specified by replaceArg (i.e. FIFO, LRU, MRU, love/hate)
	*
	* @param numbufs number of buffers in the buffer pool
	* @param replaceArg name of the buffer replacement policy
	*/
	public bufmgr(int numBufs, String replaceArg) {
		
		int page_size = global.GlobalConst.MINIBASE_PAGESIZE;
		buffPool = new byte[numBufs][page_size];
		replacementPolicy = replaceArg;
		bufferDescriptors = new BufferDescriptor[numBufs];
		pageToFrameMap = new Hashtable<>();
		pagesInThePool = new Page[numBufs];
	}
	/**
	* Pin a page
	* First check if this page is already in the buffer pool.
	* If it is, increment the pin_count and return pointer to this
	* page. If the pin_count was 0 before the call, the page was a
	* replacement candidate, but is no longer a candidate.
	* If the page is not in the pool, choose a frame (from the
	* set of replacement candidates) to hold this page, read the
	* page (using the appropriate method from diskmgr package) and pin it.
	* Also, must write out the old page in chosen frame if it is dirty
	* before reading new page. (You can assume that emptyPage == false for
	* this assignment.)
	*
	* @param pgid page number in the minibase.
	* @param page the pointer point to the page.
	* @param emptyPage true (empty page), false (nonempty
	page).
	*/
	public void pinPage(PageId pgid, Page page, boolean emptyPage, boolean loved) {
		if(pageToFrameMap.contains(pgid)){
			int indexOfThePage;
			BufferDescriptor currentDesc = bufferDescriptors[indexOfThePage=pageToFrameMap.get(pgid)];
			currentDesc.updatePinCount(1); // pinCount++;
			page = pagesInThePool[indexOfThePage];
			
		}else{
			/*
			 * 1. check if the pool is full.
			 * 2. if the pool is full; choose the candidate to replace, then if it's Dirty, write it to the disk then remove; else if the pool is not full choose a frame and add necessaries.
			 */
			if(pageToFrameMap.size() == buffPool.length){ //  the pool is full
			
				
			}else{
				
					try {
						SystemDefs.JavabaseDB.read_page(pgid, page);
					} catch (InvalidPageNumberException | FileIOException
							| IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
	}
	/**
	* Unpin a page specified by a pageId.
	* This method should be called with dirty == true if the client has
	* modified the page. If so, this call should set the dirty bit
	* for this frame. Further, if pin_count > 0, this method should
	* decrement it. If pin_count = 0 before this call, throw an excpetion
	* to report error. (for testing purposes, we ask you to throw
	* an exception named PageUnpinnedExcpetion in case of error.)
	*
	* @param pgid page number in the minibase
	* @param dirty the dirty bit of the frame.
	*/
	public void unpinPage(PageId pgid, boolean dirty, boolean loved) {
		if(pageToFrameMap.contains(pgid)){
			int index = pageToFrameMap.get(pgid);
			if(bufferDescriptors[index].getPinCount()==0){
				// throw exception PageUnpinnedExcpetion
			}
			bufferDescriptors[index].updatePinCount(-1);
			bufferDescriptors[index].setDirty(dirty);
			if(bufferDescriptors[index].getPinCount()==0){
				// Push it to be a candidate
			}
		}else{
			System.out.println("the Buffer Pool Doesn't contain this page");
		}
	}
	/**
	* Allocate new page(s).
	* Call DB Object to allocate a run of new pages and
	* find a frame in the buffer pool for the first page
	* and pin it. (This call allows a client f the Buffer Manager
	* to allocate pages on disk.) If buffer is full, i.e., you
	* can\t find a frame for the first page, ask DB to deallocate
	* all these pages, and return null.
	*
	* @param firstPage the address of the first page.
	* @param howmany total number of allocated new pages.
	*
	* @return the first page id of the new pages. null, if error.
	*/
	public PageId newPage(Page firstPage, int howmany) {
		
		if(buffPool.length == pageToFrameMap.size()){ // full
			
		}
		return null;
	}
	/**
	* This method should be called to delete a page that is on disk.
	* This routine must call the method in diskmgr package to
	* deallocate the page.
	*
	* @param pgid the page number in the database.
	*/
	public void freePage(PageId pgid) {
		try {
			SystemDefs.JavabaseDB.deallocate_page(pgid);
		} catch (InvalidRunSizeException | InvalidPageNumberException
				| FileIOException | DiskMgrException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	* Used to flush a particular page of the buffer pool to disk.
	* This method calls the write_page method of the diskmgr package.
	*
	* @param pgid the page number in the database.
	*/
	public void flushPage(PageId pgid) {
			try {
				SystemDefs.JavabaseDB.write_page(pgid, pagesInThePool[pageToFrameMap.get(pgid)]);
			} catch (InvalidPageNumberException | FileIOException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}

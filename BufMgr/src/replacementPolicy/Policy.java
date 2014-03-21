package replacementPolicy;
import java.util.*;
import bufmgr.*;

public interface Policy {

	public void pinPage(int frame);

	public void unpinPage(int frame);

	public int requestPage();
	


}

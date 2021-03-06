package uk.software.blogreader.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListAdapter;
import android.widget.ListView;
import uk.software.blogreader.ListActivity;
import uk.software.blogreader.MainActivity;
import uk.software.blogreader.R;

public class ListActivityTest extends
		ActivityInstrumentationTestCase2<ListActivity> {
	
	private ListActivity mActivity;
	private ListView mlv;
	private ListAdapter mAdapter;
	public static final int ADAPTER_COUNT = 30;
	public static final int INITIAL_POSITION = 0;
	public static final int TEST_POSITION = 5;
	
	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		setActivityInitialTouchMode(false);

	    mActivity = getActivity();

	    mlv = (ListView) mActivity.findViewById(uk.software.blogreader.R.id.listView);
	    
	    mAdapter = mlv.getAdapter();
	    
	} //End of setup method definition


	public ListActivityTest() {
		super(ListActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	public void testPreConditions() {
	    assertTrue(mlv.getOnItemSelectedListener() != null);
	    assertTrue(mAdapter != null);
	    assertEquals(mAdapter.getCount(),ADAPTER_COUNT);
	  } // end of testPreConditions() method definition
	
    public void testListViewUI(){
    	
    	mActivity.runOnUiThread(
    			new Runnable(){
    				public void run(){
    					mlv.requestFocus();
    					mlv.setSelection(INITIAL_POSITION);
    				} //End of run method definition
    			} //End of anonymous Runnable object instantiation
    			); //End of invocation of runonuithread
    }
    
    
}

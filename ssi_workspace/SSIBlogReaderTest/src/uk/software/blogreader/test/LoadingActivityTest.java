package uk.software.blogreader.test;

import android.test.ActivityInstrumentationTestCase2;
import uk.software.blogreader.LoadingActivity;

public class LoadingActivityTest extends
		ActivityInstrumentationTestCase2<LoadingActivity> {
	
	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		setActivityInitialTouchMode(false);

	    mActivity = getActivity();
	}

	private LoadingActivity mActivity;

	public LoadingActivityTest() {
		super(LoadingActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	
	

}

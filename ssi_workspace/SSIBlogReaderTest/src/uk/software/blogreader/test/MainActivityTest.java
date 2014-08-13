package uk.software.blogreader.test;

import android.test.ActivityInstrumentationTestCase2;
import uk.software.blogreader.MainActivity;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private MainActivity mActivity;

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

	public MainActivityTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	
	

}

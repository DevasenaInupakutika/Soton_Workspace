package com.prog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class progressdialog extends Activity {
    /** Called when the activity is first created. */
	
	LinearLayout ll ;
	Button btn ;
	ProgressDialog pd ;
	progress prot;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        ll = new LinearLayout(this);
        ll.setOrientation(1);
        btn = new Button(this);
        
        btn.setText("generate dialog");
        
        ll.addView(btn);
        
        setContentView(ll);
        
        // object of thread class
        pd = new ProgressDialog(this);
         prot = new progress(pd);
        
       btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(1);
		}
	});
        
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
    	// TODO Auto-generated method stub
    //	return super.onCreateDialog(id);
    	
    
    pd.setMessage("in process");
    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    return pd;
    }
    
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
    	// TODO Auto-generated method stub
    //	super.onPrepareDialog(id, dialog);
    prot.start();	
    	
    }
    
}

class progress extends Thread{
	ProgressDialog pt ;
	int state = 0 ;
	int total=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		
		
		while(state == 0){
			
			if(total == 100){
				state = 1 ;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pt.setProgress(total);
			total = total + 10 ;
		}
		
	}

	// this constructor is used to link the other activity class
	public progress(ProgressDialog pd){
		pt = pd ;
	}
	

	
}
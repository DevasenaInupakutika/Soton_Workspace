package gueei.binding.menu;

import gueei.binding.Binder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;

// Each OptionsMenuBinder correspond to one AbsMenuBridge xml. 
// Instance should be kept by the activity
public class OptionsMenuBinder {
	private boolean firstCreate = true;
	private final int mMenuResId;
	private Hashtable<Integer, AbsMenuBridge> items = 
			new Hashtable<Integer, AbsMenuBridge>();
	
	public OptionsMenuBinder(int menuResId){
		mMenuResId = menuResId;
	}
	
	// Called by owner activity
	public boolean onCreateOptionsMenu(Activity activity, Menu menu, Object model){
		// First inflate the menu - default action
		activity.getMenuInflater().inflate(mMenuResId, menu);
		
		if (firstCreate){
			// Now, parse the menu
			XmlResourceParser parser = activity.getResources().getXml(mMenuResId);
			try{
				int eventType= parser.getEventType();
				while(eventType != XmlResourceParser.END_DOCUMENT){
					if (eventType==XmlResourceParser.START_TAG){
						int id = parser.getAttributeResourceValue(Binder.ANDROID_NAMESPACE, "id", -1);
						String nodeName = parser.getName();
						if (id>0){
							AttributeSet attrs = Xml.asAttributeSet(parser);
							AbsMenuBridge item = 
									AbsMenuBridge.create(nodeName, id, attrs, activity, model);
							if (item!=null){
								items.put(id, item);
							}
						}
					}
					eventType = parser.next();
				}
			}catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException ex){
				ex.printStackTrace();
			}
			firstCreate = false;
		}
		
		for(AbsMenuBridge item: items.values()){
			item.onCreateOptionItem(menu);
		}
		
		return true;
	}
	
	public boolean onPrepareOptionsMenu(Activity activity, Menu menu){
		for(AbsMenuBridge item: items.values()){
			item.onPrepareOptionItem(menu);
		}
		return true;
	}
	
	public boolean onOptionsItemSelected(Activity activity, MenuItem mi){
		AbsMenuBridge item = items.get(mi.getItemId());
		if (item!=null){
			return item.onOptionsItemSelected(mi);
		}
		return false;
	}
}
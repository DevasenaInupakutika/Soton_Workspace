package gueei.binding.bindingProviders;

import gueei.binding.ViewAttribute;
import gueei.binding.viewAttributes.view.AnimationViewAttribute;
import gueei.binding.viewAttributes.view.BackgroundColorViewAttribute;
import gueei.binding.viewAttributes.view.BackgroundViewAttribute;
import gueei.binding.viewAttributes.view.ContextMenuViewAttribute;
import gueei.binding.viewAttributes.view.EnabledViewAttribute;
import gueei.binding.viewAttributes.view.OnClickViewEvent;
import gueei.binding.viewAttributes.view.OnGainFocusViewEvent;
import gueei.binding.viewAttributes.view.OnLongClickViewEvent;
import gueei.binding.viewAttributes.view.OnLostFocusViewEvent;
import gueei.binding.viewAttributes.view.SelectedViewAttribute;
import gueei.binding.viewAttributes.view.VisibilityViewAttribute;
import android.view.View;


public class ViewProvider extends BindingProvider {

	@Override
	public ViewAttribute<View, ?> createAttributeForView(View view, String attributeId) {
		if (attributeId.equals("enabled")){
			return new EnabledViewAttribute(view, "enabled");
		}
		else if (attributeId.equals("visibility")){
			ViewAttribute<View, Integer> attr = 
				new VisibilityViewAttribute(view, "visibility");
			return attr;
		}
		else if (attributeId.equals("background")){
			ViewAttribute<View, Object> attr = 
				new BackgroundViewAttribute(view);
			return attr;
		}
		else if (attributeId.equals("backgroundColor")){
			ViewAttribute<View, Integer> attr = 
				new BackgroundColorViewAttribute(view);
			return attr;
		}else if (attributeId.equals("contextMenu")){
			return new ContextMenuViewAttribute(view);
		}else if (attributeId.equals("onClick")){
			return new OnClickViewEvent(view);
		}else if (attributeId.equals("onLongClick")){
			return new OnLongClickViewEvent(view);
		}else if (attributeId.equals("animation")){
			return new AnimationViewAttribute(view);
		}else if (attributeId.equals("selected")){
			return new SelectedViewAttribute(view);
		}else if (attributeId.equals("onGainFocus")){
			return new OnGainFocusViewEvent(view);
		}else if (attributeId.equals("onLostFocus")){
			return new OnLostFocusViewEvent(view);
		}
		return null;
	}
}

package gueei.binding.converters;

import java.util.ArrayList;

import gueei.binding.Converter;
import gueei.binding.IObservable;
import gueei.binding.collections.CombinedAdapter;
import android.widget.Adapter;

/**
 * STITCH adapters to supply to list view
 * @author andy
 *
 */
public class STITCH extends Converter<Adapter>{
	public STITCH(IObservable<?>[] dependents) {
		super(Adapter.class, dependents);
	}

	@Override
	public Adapter calculateValue(Object... args) throws Exception {
		CombinedAdapter combine = new CombinedAdapter();
		int length = args.length;
		ArrayList<Adapter> adapters = new ArrayList<Adapter>();
		for (int i=0; i<length; i++){
			if (args[i] instanceof Adapter){
				adapters.add((Adapter)args[i]);
			}
		}
		combine.addAdapter(adapters.toArray(new Adapter[0]));
		
		return combine;
	}
}
package br.com.pirralhos.view.utils;

import java.util.List;

public class CollectionsUtils {
	public static <T> T getObject( List<T> list, T object)
	{
		if(object == null)
			return null;
		int index = list.indexOf(object);
		if(index <0)
			return null;
		return list.get(index);
	}
	
}

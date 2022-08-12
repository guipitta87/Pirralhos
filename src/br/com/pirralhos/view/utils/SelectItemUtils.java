package br.com.pirralhos.view.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.model.SelectItem;

public class SelectItemUtils {
	private static final String  GET="get";
	
	public static <T> List<SelectItem> getSelectItems(String keyFieldName, String labelFieldName,List<T> objects)
	{
		keyFieldName = keyFieldName.substring(0,1).toUpperCase().concat(keyFieldName.substring(1));
		labelFieldName = labelFieldName.substring(0,1).toUpperCase().concat(labelFieldName.substring(1));
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for(T object : objects)
		{
			try
			{
				String label = object.getClass().getMethod(GET.concat(labelFieldName), null).invoke(object,null ).toString();
				Object key = object.getClass().getMethod(GET.concat(keyFieldName), null).invoke(object,null ).toString();
				lista.add(new SelectItem(key,label));
			}
			catch (Exception e) {
				throw new FacesException(e.getMessage());
			}
		}
		return lista;
	}
	
}

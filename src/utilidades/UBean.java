package utilidades;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class UBean {
	
	public static ArrayList<Field> getAttrs(Object o)
	{
		Class clase = o.getClass();
		Field[] a = clase.getDeclaredFields();
		ArrayList<Field> attrs = new ArrayList<Field>();
		for(int i=0;i<a.length;i++)
		{
			attrs.add(a[i]);
		}
		
		return attrs;		
	}
	
	public static void goSet(Object o,String att,Object valor) {
		
	}
}

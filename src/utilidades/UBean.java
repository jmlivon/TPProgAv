package utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UBean {
	
	public static ArrayList<Field> getAttrs(Object o){
		
		Class clase = o.getClass();
		Field[] a = clase.getDeclaredFields();
		ArrayList<Field> attrs = new ArrayList<Field>();
		for(int i=0;i<a.length;i++)
		{
			attrs.add(a[i]);
		}
		
		return attrs;		
	}
	
	public static void goSet(Object o,String att,Object value) {
		
		Class clase = o.getClass();
		Method m;
		try {
			Field campo = clase.getDeclaredField(att);
			String strSet = "set" + campo.getName().substring(0, 1).toUpperCase() + campo.getName().substring(1);
			m = clase.getMethod(strSet,value.getClass());
			m.invoke(o, value);
			
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object goGet(Object o,String att) {
		
		Class clase = o.getClass();
		Method m;
		try {
			Field campo = clase.getDeclaredField(att);
			String strGet = "get" + campo.getName().substring(0, 1).toUpperCase() + campo.getName().substring(1);
			m = clase.getDeclaredMethod(strGet);
			return m.invoke(o);
			
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

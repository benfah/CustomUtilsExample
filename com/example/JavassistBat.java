package com.example;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import me.benfah.cu.util.ReflectionUtils;

public class JavassistBat
{
	private static Class<?> javassistBat;
	public static Class<?> toClass()
	{
		if(javassistBat == null)
		{
			ClassPool cp = ClassPool.getDefault();
		try
		{
			javassistBat = Class.forName("JavassistNMSBat");/*cp.get("JavassistNMSBat").toClass()*/;
		}
		catch(Exception e)
		{

			Class<?> entityBatClass = ReflectionUtils.getRefClass("{nms}.EntityBat");
			Class<?> itemClass = ReflectionUtils.getRefClass("{nms}.Item");
			Class<?> itemsClass = ReflectionUtils.getRefClass("{nms}.Items");
			Class<?> entityItemClass = ReflectionUtils.getRefClass("{nms}.EntityItem");
			try {
				CtClass javassistCt = cp.makeClass("JavassistNMSBat", cp.get(entityBatClass.getName()));
//				javassistCt.addConstructor(CtNewConstructor.make("public JavassistNMSBat(" + ReflectionUtils.getRefClass("{nms}.World").getName() + " w)"
//						+ "{"
//						+ "super(w);"
//						+ "}", javassistCt));
				CtMethod cm = CtMethod.make(""
						+ "public " + entityItemClass.getName() + " a(" + itemClass.getName() + " item, int i) {"
						+ "if(!item.equals(" + itemsClass.getName() + ".LEAD))"
						+ "return super.a(item, i);"
						+ "return null;"
						+ "}"
						+ ""
						+ ""
						+ ""
						+ "", javassistCt);
				overrideMethod(javassistCt, cm);
				javassistBat = javassistCt.toClass();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		}
		System.out.println(javassistBat.getName());
		return javassistBat;
	}
	private static CtMethod overrideMethod(CtClass ctClass, CtMethod getConnectionMethodOfSuperclass)
	        throws NotFoundException, CannotCompileException {
	    final CtMethod m = CtNewMethod.delegator(getConnectionMethodOfSuperclass, ctClass);
	    ctClass.addMethod(m);
	    return m;
	}
}

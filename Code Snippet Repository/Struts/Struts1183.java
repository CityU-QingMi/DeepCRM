    public void testConvertClass() {
        Class clazz = (Class) converter.convertValue(context, "java.util.Date", Class.class);
        assertEquals(Date.class.getName(), clazz.getName());

        Class clazz2 = (Class) converter.convertValue(context, "com.opensymphony.xwork2.util.Bar", Class.class);
        assertEquals(Bar.class.getName(), clazz2.getName());

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, "com.opensymphony.xwork2.util.IDoNotExist", Class.class));

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, new Bar(), Class.class)); // only supports string values
    }

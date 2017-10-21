    public void testOverflows() {
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Double.MAX_VALUE + "1", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Double.MIN_VALUE + "-1", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Double.MAX_VALUE + "1", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Double.MIN_VALUE + "-1", Double.class));

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Float.MAX_VALUE + "1", float.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Float.MIN_VALUE + "-1", float.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Float.MAX_VALUE + "1", Float.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Float.MIN_VALUE + "-1", Float.class));

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Integer.MAX_VALUE + "1", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Integer.MIN_VALUE + "-1", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Integer.MAX_VALUE + "1", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Integer.MIN_VALUE + "-1", Integer.class));

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Byte.MAX_VALUE + "1", byte.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Byte.MIN_VALUE + "-1", byte.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Byte.MAX_VALUE + "1", Byte.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Byte.MIN_VALUE + "-1", Byte.class));

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Short.MAX_VALUE + "1", short.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Short.MIN_VALUE + "-1", short.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Short.MAX_VALUE + "1", Short.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Short.MIN_VALUE + "-1", Short.class));

        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Long.MAX_VALUE + "1", long.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Long.MIN_VALUE + "-1", long.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Long.MAX_VALUE + "1", Long.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, Long.MIN_VALUE + "-1", Long.class));
    }

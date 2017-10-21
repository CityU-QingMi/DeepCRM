    public void testStringToInt() {
        assertEquals(new Integer(123), converter.convertValue(context, null, null, null, "123", int.class));
        context.put(ActionContext.LOCALE, Locale.US);
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123.12", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,23", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234.12", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234,12", int.class));
        context.put(ActionContext.LOCALE, Locale.GERMANY);
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123.12", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,23", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234.12", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234", int.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234,12", int.class));
    }

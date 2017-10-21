    public void testStringToInteger() {
        assertEquals(new Integer(123), converter.convertValue(context, null, null, null, "123", Integer.class));
        context.put(ActionContext.LOCALE, Locale.US);
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123.12", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", Integer.class));
        assertEquals(new Integer(1234), converter.convertValue(context, null, null, null, "1,234", Integer.class));
        // WRONG: locale separator is wrongly placed
        assertEquals(new Integer(123), converter.convertValue(context, null, null, null, "1,23", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234.12", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234,12", Integer.class));

        context.put(ActionContext.LOCALE, Locale.GERMANY);
        // WRONG: locale separator is wrongly placed
        assertEquals(new Integer(12312), converter.convertValue(context, null, null, null, "123.12", Integer.class));
        assertEquals(new Integer(1234), converter.convertValue(context, null, null, null, "1.234", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234.12", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,23", Integer.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234,12", Integer.class));
    }

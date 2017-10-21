    public void testStringToPrimitiveDouble() {
        assertEquals(123d, converter.convertValue(context, null, null, null, "123", double.class));
        context.put(ActionContext.LOCALE, Locale.US);
        assertEquals(123.12, converter.convertValue(context, null, null, null, "123.12", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", double.class));
        assertEquals(1234d, converter.convertValue(context, null, null, null, "1,234", double.class));
        assertEquals(1234.12, converter.convertValue(context, null, null, null, "1,234.12", double.class));
        assertEquals(123d, converter.convertValue(context, null, null, null, "1,23", double.class));
        assertEquals(1.234, converter.convertValue(context, null, null, null, "1.234", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234,12", double.class));

        context.put(ActionContext.LOCALE, Locale.GERMANY);
        assertEquals(12312d, converter.convertValue(context, null, null, null, "123.12", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", double.class));
        assertEquals(1.234, converter.convertValue(context, null, null, null, "1,234", double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234.12", double.class));
        assertEquals(1.23, converter.convertValue(context, null, null, null, "1,23", double.class));
        assertEquals(1234d, converter.convertValue(context, null, null, null, "1.234", double.class));
        assertEquals(1234.12, converter.convertValue(context, null, null, null, "1.234,12", double.class));
    }

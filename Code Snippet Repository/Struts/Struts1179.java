    public void testStringToDouble() {
        assertEquals(123d, converter.convertValue(context, null, null, null, "123", Double.class));
        context.put(ActionContext.LOCALE, Locale.US);
        assertEquals(new Double(123.12), converter.convertValue(context, null, null, null, "123.12", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", Double.class));
        assertEquals(new Double(1234), converter.convertValue(context, null, null, null, "1,234", Double.class));
        assertEquals(new Double(1234.12), converter.convertValue(context, null, null, null, "1,234.12", Double.class));
        // WRONG: locale separator is wrongly placed 
        assertEquals(new Double(123), converter.convertValue(context, null, null, null, "1,23", Double.class));
        assertEquals(new Double(1.234), converter.convertValue(context, null, null, null, "1.234", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1.234,12", Double.class));

        context.put(ActionContext.LOCALE, Locale.GERMANY);
        // WRONG: locale separator is wrongly placed
        assertEquals(new Double(12312), converter.convertValue(context, null, null, null, "123.12", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "123aa", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "aa123", Double.class));
        assertEquals(new Double(1.234), converter.convertValue(context, null, null, null, "1,234", Double.class));
        assertEquals(OgnlRuntime.NoConversionPossible, converter.convertValue(context, null, null, null, "1,234.12", Double.class));
        assertEquals(new Double(1.23), converter.convertValue(context, null, null, null, "1,23", Double.class));
        assertEquals(new Double(1234), converter.convertValue(context, null, null, null, "1.234", Double.class));
        assertEquals(new Double(1234.12), converter.convertValue(context, null, null, null, "1.234,12", Double.class));

    }

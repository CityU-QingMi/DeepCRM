    public void testConvertBoolean() {
        assertEquals(Boolean.TRUE, converter.convertValue(context, "true", Boolean.class));
        assertEquals(Boolean.FALSE, converter.convertValue(context, "false", Boolean.class));

        assertEquals(Boolean.TRUE, converter.convertValue(context, Boolean.TRUE, Boolean.class));
        assertEquals(Boolean.FALSE, converter.convertValue(context, Boolean.FALSE, Boolean.class));

        assertEquals(null, converter.convertValue(context, null, Boolean.class));
        assertEquals(Boolean.TRUE, converter.convertValue(context, new Bar(), Boolean.class)); // Ognl converter will default to true
    }

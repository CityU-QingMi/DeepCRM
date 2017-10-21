    public void testNotPrimitiveDefaultsToNull() {
        assertEquals(null, converter.convertValue(context, null, null, null, null, Double.class));
        assertEquals(null, converter.convertValue(context, null, null, null, "", Double.class));

        assertEquals(null, converter.convertValue(context, null, null, null, null, Integer.class));
        assertEquals(null, converter.convertValue(context, null, null, null, "", Integer.class));

        assertEquals(null, converter.convertValue(context, null, null, null, null, Float.class));
        assertEquals(null, converter.convertValue(context, null, null, null, "", Float.class));

        assertEquals(null, converter.convertValue(context, null, null, null, null, Character.class));
        assertEquals(null, converter.convertValue(context, null, null, null, "", Character.class));

        assertEquals(null, converter.convertValue(context, null, null, null, null, Long.class));
        assertEquals(null, converter.convertValue(context, null, null, null, "", Long.class));

        assertEquals(null, converter.convertValue(context, null, null, null, null, Short.class));
        assertEquals(null, converter.convertValue(context, null, null, null, "", Short.class));

    }

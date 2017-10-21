    public void testStringToPrimitives() {
        assertEquals(new Long(123), converter.convertValue(context, null, null, null, "123", long.class));
        assertEquals(new Integer(123), converter.convertValue(context, null, null, null, "123", int.class));
        assertEquals(new Double(123.5), converter.convertValue(context, null, null, null, "123.5", double.class));
        assertEquals(new Float(123.5), converter.convertValue(context, null, null, null, "123.5", float.class));
        assertEquals(false, converter.convertValue(context, null, null, null, "false", boolean.class));
        assertEquals(true, converter.convertValue(context, null, null, null, "true", boolean.class));
        assertEquals(new BigDecimal(123.5), converter.convertValue(context, null, null, null, "123.5", BigDecimal.class));
        assertEquals(new BigInteger("123"), converter.convertValue(context, null, null, null, "123", BigInteger.class));
    }

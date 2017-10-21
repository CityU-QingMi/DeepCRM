    public void testArrayToNumberConversion() {
        String[] value = new String[]{"12345"};
        assertEquals(new Integer(12345), converter.convertValue(context, null, null, null, value, Integer.class));
        assertEquals(new Long(12345), converter.convertValue(context, null, null, null, value, Long.class));
        value[0] = "123.45";
        assertEquals(new Float(123.45), converter.convertValue(context, null, null, null, value, Float.class));
        assertEquals(new Double(123.45), converter.convertValue(context, null, null, null, value, Double.class));
        value[0] = "1234567890123456789012345678901234567890";
        assertEquals(new BigInteger(value[0]), converter.convertValue(context, null, null, null, value, BigInteger.class));
        value[0] = "1234567890123456789.012345678901234567890";
        assertEquals(new BigDecimal(value[0]), converter.convertValue(context, null, null, null, value, BigDecimal.class));
    }

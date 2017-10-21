    @Test
    public void testBigDecimal()
    {
        Object obj = JSON.parse("1.0E7");
        assertTrue(obj instanceof Double);
        BigDecimal bd = BigDecimal.valueOf(10000000d);
        String string = JSON.toString(new Object[]{bd});
        obj = Array.get(JSON.parse(string),0);
        assertTrue(obj instanceof Double);
    }

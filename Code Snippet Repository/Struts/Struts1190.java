    public void testDateStrictConversion() throws Exception {
        // see XW-341
        String dateStr = "13/01/2005"; // us date format is used in context
        Object res = converter.convertValue(context, null, null, null, dateStr, Date.class);
        assertEquals(res, OgnlRuntime.NoConversionPossible);

        dateStr = "02/30/2005"; // us date format is used in context
        res = converter.convertValue(context, null, null, null, dateStr, Date.class);
        assertEquals(res, OgnlRuntime.NoConversionPossible);

        // and test a date that is passable
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        dateStr = "12/31/2005"; // us date format
        res = converter.convertValue(context, null, null, null, dateStr, Date.class);
        Date date = format.parse(dateStr);
        assertNotSame(res, OgnlRuntime.NoConversionPossible);
        assertEquals(date, res);
    }

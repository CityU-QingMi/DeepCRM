    public void testDateConversion() throws ParseException {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        assertEquals(sqlDate, converter.convertValue(context, null, null, null, sqlDate, Date.class));

        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
        Date date = format.parse("01/10/2001 00:00:00");
        String dateStr = (String) converter.convertValue(context, null, null, null, date, String.class);
        Date date2 = (Date) converter.convertValue(context, null, null, null, dateStr, Date.class);
        assertEquals(date, date2);
    }

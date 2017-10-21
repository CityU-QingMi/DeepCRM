    public void testDateConversion() throws ParseException {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        assertEquals(sqlDate, converter.convertValue(context, null, null, null, sqlDate, Date.class));

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date date = format.parse("01/10/2001 00:00:00");

        SimpleDateFormat formatt = new SimpleDateFormat("hh:mm:ss");
        java.sql.Time datet = new java.sql.Time(formatt.parse("10:11:12").getTime());

        String dateStr = (String) converter.convertValue(context, null, null, null, date, String.class);
        String datetStr = (String) converter.convertValue(context, null, null, null, datet, String.class);

        Date date2 = (Date) converter.convertValue(context, null, null, null, dateStr, Date.class);
        assertEquals(date, date2);
        java.sql.Date date3 = (java.sql.Date) converter.convertValue(context, null, null, null, dateStr, java.sql.Date.class);
        assertEquals(date, date3);
        java.sql.Timestamp ts = (java.sql.Timestamp) converter.convertValue(context, null, null, null, dateStr, java.sql.Timestamp.class);
        assertEquals(date, ts);
        java.sql.Time time1 = (java.sql.Time) converter.convertValue(context, null, null, null, datetStr, java.sql.Time.class);
        assertEquals(datet, time1);

        Date dateWithTime = format.parse("01/10/2001 01:02:03");
        Date dateRfc3339 = (Date) converter.convertValue(context, null, null, null, "2001-01-10T01:02:03", Date.class);
        assertEquals(dateWithTime, dateRfc3339);

        Date dateRfc3339DateOnly = (Date) converter.convertValue(context, null, null, null, "2001-01-10", Date.class);
        assertEquals(date, dateRfc3339DateOnly);
    }

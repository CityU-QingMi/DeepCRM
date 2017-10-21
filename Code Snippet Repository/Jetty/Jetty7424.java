    @Test
    public void test3_3()
    {
        Calendar expected = Calendar.getInstance();
        expected.set(Calendar.YEAR,1994);
        expected.set(Calendar.MONTH,Calendar.NOVEMBER);
        expected.set(Calendar.DAY_OF_MONTH,6);
        expected.set(Calendar.HOUR_OF_DAY,8);
        expected.set(Calendar.MINUTE,49);
        expected.set(Calendar.SECOND,37);
        expected.set(Calendar.MILLISECOND,0); // Milliseconds is not compared
        expected.set(Calendar.ZONE_OFFSET,0); // Use GMT+0:00
        expected.set(Calendar.DST_OFFSET,0); // No Daylight Savings Offset

        HttpFields fields = new HttpFields();

        // RFC 822 Preferred Format
        fields.put("D1","Sun, 6 Nov 1994 08:49:37 GMT");
        // RFC 822 / RFC 850 Format
        fields.put("D2","Sunday, 6-Nov-94 08:49:37 GMT");
        // RFC 850 / ANSIC C Format
        fields.put("D3","Sun Nov  6 08:49:37 1994");

        // Test parsing
        assertDate("3.3.1 RFC 822 Preferred",expected,fields.getDateField("D1"));
        assertDate("3.3.1 RFC 822 / RFC 850",expected,fields.getDateField("D2"));
        assertDate("3.3.1 RFC 850 / ANSI C",expected,fields.getDateField("D3"));

        // Test formatting
        fields.putDateField("Date",expected.getTime().getTime());
        Assert.assertEquals("3.3.1 RFC 822 preferred","Sun, 06 Nov 1994 08:49:37 GMT",fields.get("Date"));
    }

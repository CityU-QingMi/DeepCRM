    @Test
    public void testInputHasWhitespace() {
        final FastDateParser parser = new FastDateParser("M/d/y", TimeZone.getDefault(), Locale.getDefault());
        //SimpleDateFormat parser = new SimpleDateFormat("M/d/y");
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse(" 3/ 23/ 1961", parsePosition);
        Assert.assertEquals(12, parsePosition.getIndex());

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertEquals(1961, calendar.get(Calendar.YEAR));
        Assert.assertEquals(2, calendar.get(Calendar.MONTH));
        Assert.assertEquals(23, calendar.get(Calendar.DATE));       
    }

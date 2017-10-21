    @Test
    public void testInputHasMoreCharacters() {
        final FastDateParser parser = new FastDateParser("MM/dd", TimeZone.getDefault(), Locale.getDefault());
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date = parser.parse("3/23/61", parsePosition);
        Assert.assertEquals(4, parsePosition.getIndex());

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertEquals(2, calendar.get(Calendar.MONTH));
        Assert.assertEquals(23, calendar.get(Calendar.DATE));       
    }

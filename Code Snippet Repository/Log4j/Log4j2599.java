    @Test
    public void testDayNumberOfWeek() throws ParseException {
        final DateParser parser = getInstance("u");
        final Calendar calendar = Calendar.getInstance();

        calendar.setTime(parser.parse("1"));
        Assert.assertEquals(Calendar.MONDAY, calendar.get(Calendar.DAY_OF_WEEK));

        calendar.setTime(parser.parse("6"));
        Assert.assertEquals(Calendar.SATURDAY, calendar.get(Calendar.DAY_OF_WEEK));

        calendar.setTime(parser.parse("7"));
        Assert.assertEquals(Calendar.SUNDAY, calendar.get(Calendar.DAY_OF_WEEK));
    }

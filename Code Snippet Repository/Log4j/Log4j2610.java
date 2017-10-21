    @Test
    public void testInputHasWrongDay() {
        final FastDateParser parser = new FastDateParser("EEEE, MM/dd/yyy", NEW_YORK, Locale.US);
        final String input = "Thursday, 03/23/61";
        final ParsePosition parsePosition = new ParsePosition(0);
        Assert.assertNotNull(parser.parse(input, parsePosition));
        Assert.assertEquals(input.length(), parsePosition.getIndex());
        
        parsePosition.setIndex(0);
        Assert.assertNull(parser.parse( "Thorsday, 03/23/61", parsePosition));
        Assert.assertEquals(0, parsePosition.getErrorIndex());
    }

    @Test
    public void testInputHasWrongTimeZone() {
        final FastDateParser parser = new FastDateParser("mm:ss z", NEW_YORK, Locale.US);
        
        final String input = "11:23 Pacific Standard Time";
        final ParsePosition parsePosition = new ParsePosition(0);
        Assert.assertNotNull(parser.parse(input, parsePosition));
        Assert.assertEquals(input.length(), parsePosition.getIndex());
        
        parsePosition.setIndex(0);
        Assert.assertNull(parser.parse( "11:23 Pacific Standard ", parsePosition));
        Assert.assertEquals(6, parsePosition.getErrorIndex());
    }

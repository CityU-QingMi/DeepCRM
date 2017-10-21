    public void testQuartz621() {
        try {
            new CronExpression("0 0 * * * *");
            fail("Expected ParseException did not fire for wildcard day-of-month and day-of-week");
        } catch(ParseException pe) {
            assertTrue("Incorrect ParseException thrown", 
                pe.getMessage().startsWith("Support for specifying both a day-of-week AND a day-of-month parameter is not implemented."));
        }
        try {
            new CronExpression("0 0 * 4 * *");
            fail("Expected ParseException did not fire for specified day-of-month and wildcard day-of-week");
        } catch(ParseException pe) {
            assertTrue("Incorrect ParseException thrown", 
                pe.getMessage().startsWith("Support for specifying both a day-of-week AND a day-of-month parameter is not implemented."));
        }
        try {
            new CronExpression("0 0 * * * 4");
            fail("Expected ParseException did not fire for wildcard day-of-month and specified day-of-week");
        } catch(ParseException pe) {
            assertTrue("Incorrect ParseException thrown", 
                pe.getMessage().startsWith("Support for specifying both a day-of-week AND a day-of-month parameter is not implemented."));
        }
    }

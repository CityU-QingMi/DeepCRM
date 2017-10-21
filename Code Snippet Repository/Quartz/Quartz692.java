    public void testQuartz640() throws ParseException {
        try {
            new CronExpression("0 43 9 1,5,29,L * ?");
            fail("Expected ParseException did not fire for L combined with other days of the month");
        } catch(ParseException pe) {
            assertTrue("Incorrect ParseException thrown", 
                pe.getMessage().startsWith("Support for specifying 'L' and 'LW' with other days of the month is not implemented"));
        }
        try {
            new CronExpression("0 43 9 ? * SAT,SUN,L");
            fail("Expected ParseException did not fire for L combined with other days of the week");
        } catch(ParseException pe) {
            assertTrue("Incorrect ParseException thrown", 
                pe.getMessage().startsWith("Support for specifying 'L' with other days of the week is not implemented"));
        }
        try {
            new CronExpression("0 43 9 ? * 6,7,L");
            fail("Expected ParseException did not fire for L combined with other days of the week");
        } catch(ParseException pe) {
            assertTrue("Incorrect ParseException thrown", 
                pe.getMessage().startsWith("Support for specifying 'L' with other days of the week is not implemented"));
        }
        try {
            new CronExpression("0 43 9 ? * 5L");
        } catch(ParseException pe) {
            fail("Unexpected ParseException thrown for supported '5L' expression.");
        }
    }

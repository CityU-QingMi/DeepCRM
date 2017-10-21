    private void assertSequence(ZonedDateTime midnight, Object[][] expected)
    {
        ZonedDateTime nextEvent = midnight;
        
        for (int i = 0; i < expected.length; i++)
        {
            long currentMillis = nextEvent.toInstant().toEpochMilli();
            nextEvent = nextEvent.toLocalDate().plus(1, ChronoUnit.DAYS).atStartOfDay(nextEvent.getZone());                
            assertThat("Next Event", toString(nextEvent), is(expected[i][0]));
            long duration = (nextEvent.toInstant().toEpochMilli() - currentMillis);
            assertThat("Duration to next event", duration, is((long) expected[i][1]));
        }
    }

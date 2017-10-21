    @Test
    public void testMidnightRolloverCalc_PST_DST_End()
    {
        ZoneId zone = toZoneId("PST");
        ZonedDateTime initialDate = toDateTime("2016.11.03-11:22:33.0 AM PDT", zone);
    
        ZonedDateTime midnight = RolloverFileOutputStream.toMidnight(initialDate);
        assertThat("Midnight", toString(midnight), is("2016.11.04-12:00:00.0 AM PDT"));
    
        Object expected[][] = {
                {"2016.11.05-12:00:00.0 AM PDT", 86_400_000L},
                {"2016.11.06-12:00:00.0 AM PDT", 86_400_000L},
                {"2016.11.07-12:00:00.0 AM PST", 90_000_000L}, // the long day
                {"2016.11.08-12:00:00.0 AM PST", 86_400_000L},
                {"2016.11.09-12:00:00.0 AM PST", 86_400_000L},
        };
    
        assertSequence(midnight, expected);
    }

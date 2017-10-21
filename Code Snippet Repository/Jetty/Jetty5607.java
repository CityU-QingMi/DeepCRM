    @Test
    public void testMidnightRolloverCalc_PST_DST_Start()
    {
        ZoneId zone = toZoneId("PST");
        ZonedDateTime initialDate = toDateTime("2016.03.10-01:23:45.0 PM PST", zone);
        
        ZonedDateTime midnight = RolloverFileOutputStream.toMidnight(initialDate);
        assertThat("Midnight", toString(midnight), is("2016.03.11-12:00:00.0 AM PST"));
        
        Object expected[][] = {
                {"2016.03.12-12:00:00.0 AM PST", 86_400_000L},
                {"2016.03.13-12:00:00.0 AM PST", 86_400_000L},
                {"2016.03.14-12:00:00.0 AM PDT", 82_800_000L}, // the short day
                {"2016.03.15-12:00:00.0 AM PDT", 86_400_000L},
                {"2016.03.16-12:00:00.0 AM PDT", 86_400_000L},
        };
    
        assertSequence(midnight, expected);
    }

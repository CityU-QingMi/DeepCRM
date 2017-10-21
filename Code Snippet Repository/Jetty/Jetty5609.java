    @Test
    public void testMidnightRolloverCalc_Sydney_DST_Start()
    {
        ZoneId zone = toZoneId("Australia/Sydney");
        ZonedDateTime initialDate = toDateTime("2016.09.31-01:23:45.0 PM AEST", zone);
    
        ZonedDateTime midnight = RolloverFileOutputStream.toMidnight(initialDate);
        assertThat("Midnight", toString(midnight), is("2016.10.01-12:00:00.0 AM AEST"));
    
        Object expected[][] = {
                {"2016.10.02-12:00:00.0 AM AEST", 86_400_000L},
                {"2016.10.03-12:00:00.0 AM AEDT", 82_800_000L}, // the short day
                {"2016.10.04-12:00:00.0 AM AEDT", 86_400_000L},
                {"2016.10.05-12:00:00.0 AM AEDT", 86_400_000L},
                {"2016.10.06-12:00:00.0 AM AEDT", 86_400_000L},
        };
        
        assertSequence(midnight, expected);
    }

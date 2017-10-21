    @Test
    public void testMidnightRolloverCalc_Sydney_DST_End()
    {
        ZoneId zone = toZoneId("Australia/Sydney");
        ZonedDateTime initialDate = toDateTime("2016.04.01-11:22:33.0 AM AEDT", zone);
    
        ZonedDateTime midnight = RolloverFileOutputStream.toMidnight(initialDate);
        assertThat("Midnight", toString(midnight), is("2016.04.02-12:00:00.0 AM AEDT"));
    
        Object expected[][] = {
                {"2016.04.03-12:00:00.0 AM AEDT", 86_400_000L},
                {"2016.04.04-12:00:00.0 AM AEST", 90_000_000L}, // The long day
                {"2016.04.05-12:00:00.0 AM AEST", 86_400_000L},
                {"2016.04.06-12:00:00.0 AM AEST", 86_400_000L},
                {"2016.04.07-12:00:00.0 AM AEST", 86_400_000L},
        };
    
        assertSequence(midnight, expected);
    }

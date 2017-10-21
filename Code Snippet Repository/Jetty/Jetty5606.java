    @Test
    public void testMidnightRolloverCalc_PDT_Issue1507()
    {
        ZoneId zone = toZoneId("PST");
        ZonedDateTime initialDate = toDateTime("2017.04.26-08:00:00.0 PM PDT", zone);
        
        ZonedDateTime midnight = RolloverFileOutputStream.toMidnight(initialDate);
        assertThat("Midnight", toString(midnight), is("2017.04.27-12:00:00.0 AM PDT"));
        
        Object expected[][] = {
                {"2017.04.27-12:00:00.0 AM PDT", 14_400_000L}, 
                {"2017.04.28-12:00:00.0 AM PDT", 86_400_000L}, 
                {"2017.04.29-12:00:00.0 AM PDT", 86_400_000L},
                {"2017.04.30-12:00:00.0 AM PDT", 86_400_000L},
                {"2017.05.01-12:00:00.0 AM PDT", 86_400_000L},
                {"2017.05.02-12:00:00.0 AM PDT", 86_400_000L},
        };
        
        assertSequence(initialDate, expected);
    }

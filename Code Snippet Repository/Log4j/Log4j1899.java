    @Test
    public void testCompareTo() {
        assertEquals(-1, Duration.parse("PT1S").compareTo(Duration.parse("PT2S")));
        assertEquals(-1, Duration.parse("PT1M").compareTo(Duration.parse("PT2M")));
        assertEquals(-1, Duration.parse("PT1H").compareTo(Duration.parse("PT2H")));
        assertEquals(-1, Duration.parse("P1D").compareTo(Duration.parse("P2D")));

        assertEquals(0, Duration.parse("PT1S").compareTo(Duration.parse("PT1S")));
        assertEquals(0, Duration.parse("PT1M").compareTo(Duration.parse("PT1M")));
        assertEquals(0, Duration.parse("PT1H").compareTo(Duration.parse("PT1H")));
        assertEquals(0, Duration.parse("P1D").compareTo(Duration.parse("P1D")));

        assertEquals(1, Duration.parse("PT2S").compareTo(Duration.parse("PT1S")));
        assertEquals(1, Duration.parse("PT2M").compareTo(Duration.parse("PT1M")));
        assertEquals(1, Duration.parse("PT2H").compareTo(Duration.parse("PT1H")));
        assertEquals(1, Duration.parse("P2D").compareTo(Duration.parse("P1D")));

        assertEquals(0, Duration.parse("PT1M").compareTo(Duration.parse("PT60S")));
        assertEquals(0, Duration.parse("PT1H").compareTo(Duration.parse("PT60M")));
        assertEquals(0, Duration.parse("PT1H").compareTo(Duration.parse("PT3600S")));
        assertEquals(0, Duration.parse("P1D").compareTo(Duration.parse("PT24H")));
        assertEquals(0, Duration.parse("P1D").compareTo(Duration.parse("PT1440M")));
    }

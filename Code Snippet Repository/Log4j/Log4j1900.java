    @Test
    public void testEquals() {
        assertNotEquals(Duration.parse("PT1S"),(Duration.parse("PT2S")));
        assertNotEquals(Duration.parse("PT1M"),(Duration.parse("PT2M")));
        assertNotEquals(Duration.parse("PT1H"),(Duration.parse("PT2H")));
        assertNotEquals(Duration.parse("P1D"),(Duration.parse("P2D")));

        assertEquals( Duration.parse("PT1S"),(Duration.parse("PT1S")));
        assertEquals( Duration.parse("PT1M"),(Duration.parse("PT1M")));
        assertEquals( Duration.parse("PT1H"),(Duration.parse("PT1H")));
        assertEquals( Duration.parse("P1D"),(Duration.parse("P1D")));

        assertEquals( Duration.parse("PT1M"),(Duration.parse("PT60S")));
        assertEquals( Duration.parse("PT1H"),(Duration.parse("PT60M")));
        assertEquals( Duration.parse("PT1H"),(Duration.parse("PT3600S")));
        assertEquals( Duration.parse("P1D"),(Duration.parse("PT24H")));
        assertEquals(Duration.parse("P1D"), (Duration.parse("PT1440M")));
    }

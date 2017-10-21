    @Test
    public void testToMillis() {
        assertEquals(0, Duration.ZERO.toMillis());
        assertEquals(1000, Duration.parse("PT1S").toMillis());
        assertEquals(1000 * 2 * 60, Duration.parse("PT2M").toMillis());
        assertEquals(1000 * 3 * 60 * 60, Duration.parse("PT3H").toMillis());
        assertEquals(1000 * 4 * 24 * 60 * 60, Duration.parse("P4D").toMillis());
        final long expected = (1000 * 4 * 24 * 60 * 60) + (1000 * 3 * 60 * 60) + (1000 * 2 * 60) + 1000;
        assertEquals(expected, Duration.parse("P4DT3H2M1S").toMillis());
    }

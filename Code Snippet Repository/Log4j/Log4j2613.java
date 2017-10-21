    @Test
    public void testFormatLong_goingBackInTime() {
        final long now = System.currentTimeMillis();
        final long start = now - TimeUnit.HOURS.toMillis(25);
        final long end = now + TimeUnit.HOURS.toMillis(25);
        for (final FixedFormat format : FixedFormat.values()) {
            final SimpleDateFormat simpleDF = new SimpleDateFormat(format.getPattern(), Locale.getDefault());
            final FixedDateFormat customTF = new FixedDateFormat(format, TimeZone.getDefault());
            for (long time = end; time > start; time -= 12345) {
                final String actual = customTF.format(time);
                final String expected = simpleDF.format(new Date(time));
                assertEquals(format + "(" + format.getPattern() + ")" + "/" + time, expected, actual);
            }
        }
    }

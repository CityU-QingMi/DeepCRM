    @Test
    public void testFormatLong_goingBackInTime_DST() {
        final Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        instance.set(2017, 2, 12, 2, 0);
        final long now = instance.getTimeInMillis();
        final long start = now - TimeUnit.HOURS.toMillis(1);
        final long end = now + TimeUnit.HOURS.toMillis(1);

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

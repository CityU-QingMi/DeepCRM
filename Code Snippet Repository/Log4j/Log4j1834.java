    @Test
    public void testDontInterpretBackslashAsEscape() {
        final PatternProcessor pp = new PatternProcessor("c:\\test\\new/app-%d{HH-mm-ss}.log");
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 16);
        cal.set(Calendar.MINUTE, 02);
        cal.set(Calendar.SECOND, 15);

        final StringBuilder buf = new StringBuilder();
        pp.formatFileName(buf, cal.getTime(), 23);
        assertEquals("c:\\test\\new/app-16-02-15.log", buf.toString());
    }

    @Test
    public void testAfterWaitStillLessThan2Millis() throws Exception {
        Thread.sleep(100);
        final long millis1 = new SystemClock().currentTimeMillis();
        final long sysMillis = System.currentTimeMillis();

        final long diff = sysMillis - millis1;

        assertTrue("diff too large: " + diff, diff <= 1);
    }

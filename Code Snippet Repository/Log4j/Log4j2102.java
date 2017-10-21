    @Test
    public void testCompareToOverflow() {
        // no overflow, but close
        final Delayed d1 = BurstFilter.createLogDelay(Long.MAX_VALUE - TimeUnit.SECONDS.toNanos(10) - System.nanoTime());

        // Overflow
        final Delayed d2 = BurstFilter.createLogDelay(Long.MAX_VALUE + TimeUnit.SECONDS.toNanos(10) - System.nanoTime());

        assertThat(d2, is(greaterThan(d1)));
    }

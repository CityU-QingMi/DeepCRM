    @Test
    public void testUncachedThreadNameStrategyReturnsCurrentThreadName() throws Exception {
        final String name1 = "MODIFIED-THREADNAME1";
        Thread.currentThread().setName(name1);
        assertEquals(name1, ThreadNameCachingStrategy.UNCACHED.getThreadName());

        final String name2 = "OTHER-THREADNAME2";
        Thread.currentThread().setName(name2);
        assertEquals(name2, ThreadNameCachingStrategy.UNCACHED.getThreadName());
    }

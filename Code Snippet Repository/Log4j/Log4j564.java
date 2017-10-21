    @Test
    public void testBoxIsThreadLocal() throws Exception {
        final StringBuilder[] probe = new StringBuilder[16 * 3];
        populate(0, probe);
        final Thread t1 = new Thread() {
            @Override
            public void run() {
                populate(16, probe);
            }
        };
        t1.start();
        t1.join();
        final Thread t2 = new Thread() {
            @Override
            public void run() {
                populate(16, probe);
            }
        };
        t2.start();
        t2.join();
        for (int i = 0; i < probe.length - 16; i++) {
            for (int j = 1; j < 16; j++) {
                assertNotSame("probe[" + i +"]=" + probe[i] + ", probe[" + (i + j) +"]=" + probe[i + j],
                        probe[i], probe[i + j]);
            }
        }
    }

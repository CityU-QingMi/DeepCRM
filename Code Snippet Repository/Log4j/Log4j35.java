    @Test
    public void testAdditivity2() {
        final Logger a = Logger.getLogger("a");
        final Logger ab = Logger.getLogger("a.b");
        final Logger abc = Logger.getLogger("a.b.c");
        final Logger x = Logger.getLogger("x");

        final CountingAppender ca1 = new CountingAppender();
        ca1.start();
        final CountingAppender ca2 = new CountingAppender();
        ca2.start();

        try {
            a.getLogger().addAppender(ca1);
            abc.getLogger().addAppender(ca2);

            assertEquals(ca1.counter, 0);
            assertEquals(ca2.counter, 0);

            ab.debug(MSG);
            assertEquals(ca1.counter, 1);
            assertEquals(ca2.counter, 0);

            abc.debug(MSG);
            assertEquals(ca1.counter, 2);
            assertEquals(ca2.counter, 1);

            x.debug(MSG);
            assertEquals(ca1.counter, 2);
            assertEquals(ca2.counter, 1);
            ca1.stop();
            ca2.stop();
        } finally {
            a.getLogger().removeAppender(ca1);
            abc.getLogger().removeAppender(ca2);
        }}

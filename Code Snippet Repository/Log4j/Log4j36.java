    @Test
    public void testAdditivity3() {
        final Logger root = Logger.getRootLogger();
        final Logger a = Logger.getLogger("a");
        final Logger ab = Logger.getLogger("a.b");
        final Logger abc = Logger.getLogger("a.b.c");
        Logger.getLogger("x");

        final CountingAppender caRoot = new CountingAppender();
        caRoot.start();
        final CountingAppender caA = new CountingAppender();
        caA.start();
        final CountingAppender caABC = new CountingAppender();
        caABC.start();
        try {
            root.getLogger().addAppender(caRoot);
            a.getLogger().addAppender(caA);
            abc.getLogger().addAppender(caABC);

            assertEquals(caRoot.counter, 0);
            assertEquals(caA.counter, 0);
            assertEquals(caABC.counter, 0);

            ab.setAdditivity(false);

            a.debug(MSG);
            assertEquals(caRoot.counter, 1);
            assertEquals(caA.counter, 1);
            assertEquals(caABC.counter, 0);

            ab.debug(MSG);
            assertEquals(caRoot.counter, 1);
            assertEquals(caA.counter, 1);
            assertEquals(caABC.counter, 0);

            abc.debug(MSG);
            assertEquals(caRoot.counter, 1);
            assertEquals(caA.counter, 1);
            assertEquals(caABC.counter, 1);
            caRoot.stop();
            caA.stop();
            caABC.stop();
        } finally {
            root.getLogger().removeAppender(caRoot);
            a.getLogger().removeAppender(caA);
            abc.getLogger().removeAppender(caABC);
        }}

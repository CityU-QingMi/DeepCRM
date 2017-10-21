    @Test
    public void testAdditivity1() {
        final Logger loggerA = Logger.getLogger("a");
        final Logger loggerAB = Logger.getLogger("a.b");
        final CountingAppender coutingAppender = new CountingAppender();
        coutingAppender.start();
        try {
            loggerA.getLogger().addAppender(coutingAppender);

            assertEquals(0, coutingAppender.counter);
            loggerAB.debug(MSG);
            assertEquals(1, coutingAppender.counter);
            loggerAB.info(MSG);
            assertEquals(2, coutingAppender.counter);
            loggerAB.warn(MSG);
            assertEquals(3, coutingAppender.counter);
            loggerAB.error(MSG);
            assertEquals(4, coutingAppender.counter);
            coutingAppender.stop();
        } finally {
            loggerA.getLogger().removeAppender(coutingAppender);
        }
    }

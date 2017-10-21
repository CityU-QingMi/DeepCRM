    @Test
    public void testStartAndStop() throws Exception {
        setUp("name");

        appender.start();
        then(manager).should().startupInternal();

        appender.stop();
        then(manager).should().stop(0L, TimeUnit.MILLISECONDS);
    }

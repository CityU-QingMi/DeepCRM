    @Test
    public void testInitAndDestroy() throws Exception {
        this.listener.contextInitialized(this.event);

        then(initializer).should().start();
        then(initializer).should().setLoggerContext();

        this.listener.contextDestroyed(this.event);

        then(initializer).should().clearLoggerContext();
        then(initializer).should().stop();
    }

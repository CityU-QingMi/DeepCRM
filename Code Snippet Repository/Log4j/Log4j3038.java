    @Test
    public void testInitAndDestroy() throws Exception {
        this.filter.init(this.filterConfig);

        then(initializer).should().clearLoggerContext();

        this.filter.destroy();

        then(initializer).should().setLoggerContext();
    }

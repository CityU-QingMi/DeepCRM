    @Test
    public void testNoErrorIfLogAfterShutdown() throws Exception {
        final Logger log = LogManager.getLogger("com.foo.Bar");
        final String msg = "Async logger msg";
        log.info(msg, new InternalError("this is not a real error"));
        CoreLoggerContexts.stopLoggerContext(); // stop async thread

        // call the #logMessage() method to bypass the isEnabled check: 
        // before the LOG4J2-639 fix this would throw a NPE
        ((AbstractLogger) log).logMessage("com.foo.Bar", Level.INFO, null, new SimpleMessage("msg"), null);
    }

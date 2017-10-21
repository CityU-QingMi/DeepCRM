    @Test
    public void testIncludeLocationDefaultsToFalse() {
    	final LoggerConfig rootLoggerConfig =
    			AsyncLoggerConfig.RootLogger.createLogger(
    					null, "INFO", null, new AppenderRef[0], null, new DefaultConfiguration(), null);
    	assertFalse("Include location should default to false for async logggers",
    			    rootLoggerConfig.isIncludeLocation());

    	final LoggerConfig loggerConfig =
    	        AsyncLoggerConfig.createLogger(
    	        		null, "INFO", "com.foo.Bar", null, new AppenderRef[0], null, new DefaultConfiguration(),
    	        		null);
    	assertFalse("Include location should default to false for async logggers",
    			    loggerConfig.isIncludeLocation());
    }

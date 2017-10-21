    @Test
    public void testLogStackTraceWithClassThatCannotInitialize() {
        try {
            // Try to create the object, which will always fail during class initialization
            final AlwaysThrowsError error = new AlwaysThrowsError();

            // If the error was not triggered then fail
            fail("Test did not throw expected error: " + error);
        } catch (final Throwable e) {
            // Print the stack trace to System.out for informational purposes
            // System.err.println("### Here's the stack trace that we'll log with log4j ###");
            // e.printStackTrace();
            // System.err.println("### End stack trace ###");

            final Logger logger = LogManager.getLogger(getClass());

            // This is the critical portion of the test. The log message must be printed without
            // throwing a java.lang.Error when introspecting the AlwaysThrowError class in the
            // stack trace.
            logger.error(e.getMessage(), e);
            logger.error(e);
        }
    }

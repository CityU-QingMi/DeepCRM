    public SLF4JLoggerContextFactory() {
        // LOG4J2-230, LOG4J2-204 (improve error reporting when misconfigured)
        boolean misconfigured = false;
        try {
            LoaderUtil.loadClass("org.slf4j.helpers.Log4jLoggerFactory");
            misconfigured = true;
        } catch (final ClassNotFoundException classNotFoundIsGood) {
            LOGGER.debug("org.slf4j.helpers.Log4jLoggerFactory is not on classpath. Good!");
        }
        if (misconfigured) {
            throw new IllegalStateException("slf4j-impl jar is mutually exclusive with log4j-to-slf4j jar "
                    + "(the first routes calls from SLF4J to Log4j, the second from Log4j to SLF4J)");
        }
    }

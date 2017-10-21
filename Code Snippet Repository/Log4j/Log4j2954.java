    static synchronized Log4jTaglibLoggerContext getInstance(final ServletContext servletContext) {
        Log4jTaglibLoggerContext loggerContext = CONTEXTS.get(servletContext);
        if (loggerContext != null) {
            return loggerContext;
        }

        synchronized (CONTEXTS) {
            loggerContext = CONTEXTS.get(servletContext);
            if (loggerContext == null) {
                loggerContext = new Log4jTaglibLoggerContext(servletContext);
                CONTEXTS.put(servletContext, loggerContext);
            }
        }

        return loggerContext;
    }

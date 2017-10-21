    public static Runnable wrapExecutionContext(final ServletContext servletContext, final Runnable runnable) {
        return new Runnable() {
            @Override
            public void run() {
                final Log4jWebSupport webSupport = getWebLifeCycle(servletContext);
                webSupport.setLoggerContext();
                try {
                    runnable.run();
                } finally {
                    webSupport.clearLoggerContext();
                }
            }
        };
    }

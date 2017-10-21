    public static Log4jWebLifeCycle getWebLifeCycle(final ServletContext servletContext) {
        WEB_SUPPORT_LOOKUP.lock();
        try {
            Log4jWebLifeCycle webLifeCycle = (Log4jWebLifeCycle) servletContext.getAttribute(
                Log4jWebSupport.SUPPORT_ATTRIBUTE);
            if (webLifeCycle == null) {
                webLifeCycle = Log4jWebInitializerImpl.initialize(servletContext);
            }
            return webLifeCycle;
        } finally {
            WEB_SUPPORT_LOOKUP.unlock();
        }
    }

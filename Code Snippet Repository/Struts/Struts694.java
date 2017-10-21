    public void contextInitialized(ServletContextEvent sce) {
        InitOperations init = new InitOperations();
        Dispatcher dispatcher = null;
        try {
            ListenerHostConfig config = new ListenerHostConfig(sce.getServletContext());
            init.initLogging(config);
            dispatcher = init.initDispatcher(config);
            init.initStaticContentLoader(config, dispatcher);

            prepare = new PrepareOperations(dispatcher);
            sce.getServletContext().setAttribute(StrutsStatics.SERVLET_DISPATCHER, dispatcher);
        } finally {
            if (dispatcher != null) {
                dispatcher.cleanUpAfterInit();
            }
            init.cleanup();
        }
    }

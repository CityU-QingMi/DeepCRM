    public JspRuntimeContext(ServletContext context, Options options) {

        this.context = context;
        this.options = options;

        // Get the parent class loader
        parentClassLoader =
                (URLClassLoader) Thread.currentThread().getContextClassLoader();
        if (parentClassLoader == null) {
            parentClassLoader =
                    (URLClassLoader) this.getClass().getClassLoader();
        }

        if (log.isDebugEnabled()) {
            if (parentClassLoader != null) {
                log.debug(Localizer.getMessage("jsp.message.parent_class_loader_is",
                        parentClassLoader.toString()));
            } else {
                log.debug(Localizer.getMessage("jsp.message.parent_class_loader_is",
                        "<none>"));
            }
        }

        initClassPath();

        if (context instanceof JspCServletContext) {
            return;
        }

        if (Constants.IS_SECURITY_ENABLED) {
            initSecurity();
        }

        // If this web application context is running from a
        // directory, start the background compilation thread
        String appBase = context.getRealPath("/");
        if (!options.getDevelopment()
                && appBase != null
                && options.getCheckInterval() > 0) {
            lastCheck = System.currentTimeMillis();
        }
    }

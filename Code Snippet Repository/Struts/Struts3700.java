    public void init() throws ServletException {
        try {
            Dispatcher dispatcher = (Dispatcher) getServletContext().getAttribute(StrutsStatics.SERVLET_DISPATCHER);
            if (dispatcher == null) {
                throw new IllegalStateException("Unable to find the Dispatcher in the Servlet Context. Is '" + StrutsListener.class.getName() + "' missing in web.xml?");
            }
            freemarkerManager = dispatcher.getContainer().getInstance(FreemarkerManager.class);
            config = createConfiguration();

            // Process object_wrapper init-param out of order:
            wrapper = config.getObjectWrapper();
            LOG.debug("Using object wrapper of class {}", wrapper.getClass().getName());

            // Process all other init-params:
            Enumeration initpnames = getServletConfig().getInitParameterNames();
            while (initpnames.hasMoreElements()) {
                String name = (String) initpnames.nextElement();
                String value = getInitParameter(name);
                if (name == null) {
                    throw new ServletException("init-param without param-name. Maybe the web.xml is not well-formed?");
                }
                if (value == null) {
                    throw new ServletException("init-param without param-value. Maybe the web.xml is not well-formed?");
                }

                // template path is already handled!
                if (!FreemarkerManager.INITPARAM_TEMPLATE_PATH.equals(name)) freemarkerManager.addSetting(name, value);
            }
            nocache = freemarkerManager.getNocache();
            debug = freemarkerManager.getDebug();
            contentType = freemarkerManager.getContentType();
            noCharsetInContentType = freemarkerManager.getNoCharsetInContentType();
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

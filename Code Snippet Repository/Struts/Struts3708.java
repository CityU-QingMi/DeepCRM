    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Dispatcher dispatcher = (Dispatcher) getServletContext().getAttribute(StrutsStatics.SERVLET_DISPATCHER);
        if (dispatcher == null) {
            throw new IllegalStateException("Unable to find the Dispatcher in the Servlet Context. Is '" + StrutsListener.class.getName() + "' missing in web.xml?");
        }
        velocityManager = dispatcher.getContainer().getInstance(VelocityManager.class);
        velocityManager.init(config.getServletContext());

        // do whatever we have to do to init Velocity
        getVelocityView().setVelocityEngine(velocityManager.getVelocityEngine());
        // toolboxManager = velocityManager.getToolboxManager();

        
        // we can get these now that velocity is initialized
        defaultContentType = getVelocityProperty(VelocityView.CONTENT_TYPE_KEY, VelocityView.DEFAULT_CONTENT_TYPE);

        String encoding = getVelocityProperty(RuntimeConstants.OUTPUT_ENCODING, VelocityView.DEFAULT_OUTPUT_ENCODING);

        // For non Latin-1 encodings, ensure that the charset is
        // included in the Content-Type header.
        if (!VelocityView.DEFAULT_OUTPUT_ENCODING.equalsIgnoreCase(encoding)) {
            int index = defaultContentType.lastIndexOf("charset");
            if (index < 0) {
                // the charset specifier is not yet present in header.
                // append character encoding to default content-type
                defaultContentType += "; charset=" + encoding;
            } else {
                // The user may have configuration issues.
                getVelocityView().getVelocityEngine().getLog().warn("VelocityViewServlet: Charset was already " + "specified in the Content-Type property.  " + "Output encoding property will be ignored.");
            }
        }

        getVelocityView().getVelocityEngine().getLog().info("VelocityViewServlet: Default content-type is: " + defaultContentType);
    }

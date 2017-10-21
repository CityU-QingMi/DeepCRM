    protected void initServletContext() {

        context = new JspCServletContext
                (new PrintWriter(System.out),
                        classLoaderInterface);
        tldLocationsCache = new TldLocationsCache(context, true);

        rctxt = new JspRuntimeContext(context, this);
        jspConfig = new JspConfig(context);
        tagPluginManager = new TagPluginManager(context);
    }

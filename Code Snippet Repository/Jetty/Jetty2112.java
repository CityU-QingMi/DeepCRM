    @Override
    public void parseWebInfClasses(WebAppContext context, org.eclipse.jetty.annotations.AnnotationParser parser)
    throws Exception
    {
        Bundle webbundle = (Bundle) context.getAttribute(OSGiWebappConstants.JETTY_OSGI_BUNDLE);
        String bundleClasspath = (String)webbundle.getHeaders().get(Constants.BUNDLE_CLASSPATH);
        //only scan WEB-INF/classes if we didn't already scan it with parseWebBundle
        if (StringUtil.isBlank(bundleClasspath) || !bundleClasspath.contains("WEB-INF/classes"))
            super.parseWebInfClasses(context, parser);
    }

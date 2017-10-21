    @Override
    protected void addSpringOSGiSupport() {
        // see the javadoc for org.springframework.osgi.web.context.support.OsgiBundleXmlWebApplicationContext for more details
        // OsgiBundleXmlWebApplicationContext expects the the BundleContext to be set in the ServletContext under the attribute
        // OsgiBundleXmlWebApplicationContext.BUNDLE_CONTEXT_ATTRIBUTE
        try {
            Class clazz = Class.forName("org.springframework.osgi.web.context.support.OsgiBundleXmlWebApplicationContext");
            String key = (String) clazz.getDeclaredField("BUNDLE_CONTEXT_ATTRIBUTE").get(null);
            servletContext.setAttribute(key, felix.getBundleContext());
        } catch (ClassNotFoundException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Spring OSGi support is not enabled");
            }
        } catch (Exception e) {
            LOG.error("The API of Spring OSGi has changed and the field [{}] is no longer available. The OSGi plugin needs to be updated",
                        "org.springframework.osgi.web.context.support.OsgiBundleXmlWebApplicationContext.BUNDLE_CONTEXT_ATTRIBUTE", e);
        }
    }

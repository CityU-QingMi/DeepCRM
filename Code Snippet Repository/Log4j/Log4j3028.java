    private ClassLoader getClassLoader() {
        try {
            // if container is Servlet 3.0, use its getClassLoader method
            // this may look odd, but the call below will throw NoSuchMethodError if user is on Servlet 2.5
            // we compile against 3.0 to support Log4jServletContainerInitializer, but we don't require 3.0
            return this.servletContext.getClassLoader();
        } catch (final Throwable ignore) {
            // LOG4J2-248: use TCCL if possible
            return LoaderUtil.getThreadContextClassLoader();
        }
    }

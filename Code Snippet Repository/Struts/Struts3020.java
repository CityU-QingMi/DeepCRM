    public PageContext getPageContext(Servlet servlet, ServletRequest request,
            ServletResponse response, String errorPageURL, boolean needsSession,
            int bufferSize, boolean autoflush) {

        if (servlet.getClass().getName().startsWith("org.apache.struts2.jsp")) {
            if( Constants.IS_SECURITY_ENABLED ) {
                PrivilegedGetPageContext dp = new PrivilegedGetPageContext(
                        (JspFactoryImpl)this, servlet, request, response, errorPageURL,
                        needsSession, bufferSize, autoflush);
                return (PageContext)AccessController.doPrivileged(dp);
            } else {
                return internalGetPageContext(servlet, request, response,
                        errorPageURL, needsSession,
                        bufferSize, autoflush);
            }
        } else {
            //this call is coming from a page is that is getting handled by jasper, so use the jasper factory instead
            return jasperFactoryImpl.getPageContext(servlet, request, response, errorPageURL, autoflush, bufferSize, autoflush);
        }
    }

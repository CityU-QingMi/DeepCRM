    private void serviceJspFile(HttpServletRequest request,
                                HttpServletResponse response, String jspUri,
                                Throwable exception, boolean precompile)
        throws ServletException, IOException {

        JspServletWrapper wrapper =
            (JspServletWrapper) rctxt.getWrapper(jspUri);
        if (wrapper == null) {
            synchronized(this) {
                wrapper = (JspServletWrapper) rctxt.getWrapper(jspUri);
                if (wrapper == null) {
                    // Check if the requested JSP page exists, to avoid
                    // creating unnecessary directories and files.
                    if (null == context.getResource(jspUri)) {
                        String includeRequestUri = (String)
                        request.getAttribute(
                                "javax.servlet.include.request_uri");
                        if (includeRequestUri != null) {
                            // This file was included. Throw an exception as
                            // a response.sendError() will be ignored
                            String msg = Localizer.getMessage(
                                    "jsp.error.file.not.found",jspUri);
                            // Strictly, filtering this is an application
                            // responsibility but just in case...
                            throw new ServletException(
                                    SecurityUtil.filter(msg));
                        } else {
                            try {
                                response.sendError(
                                        HttpServletResponse.SC_NOT_FOUND,
                                        request.getRequestURI());
                            } catch (IllegalStateException ise) {
                                log.error(Localizer.getMessage(
                                        "jsp.error.file.not.found",
                                        jspUri));
                            }
                        }
                        return;
                    }
                    boolean isErrorPage = exception != null;
                    wrapper = new JspServletWrapper(config, options, jspUri,
                                                    isErrorPage, rctxt);
                    rctxt.addWrapper(jspUri,wrapper);
                }
            }
        }

        wrapper.service(request, response, precompile);

    }

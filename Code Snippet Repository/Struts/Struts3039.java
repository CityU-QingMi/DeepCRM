    public static void include(ServletRequest request,
                               ServletResponse response,
                               String relativePath,
                               JspWriter out,
                               boolean flush)
            throws IOException, ServletException {

        if (flush && !(out instanceof BodyContent))
            out.flush();

        // FIXME - It is tempting to use request.getRequestDispatcher() to
        // resolve a relative path directly, but Catalina currently does not
        // take into account whether the caller is inside a RequestDispatcher
        // include or not.  Whether Catalina *should* take that into account
        // is a spec issue currently under review.  In the mean time,
        // replicate Jasper's previous behavior

        String resourcePath = getContextRelativePath(request, relativePath);
        RequestDispatcher rd = request.getRequestDispatcher(resourcePath);

        rd.include(request,
                new ServletResponseWrapperInclude(response, out));

    }

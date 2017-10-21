    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        // note enctype="multipart/form-data" does not send parameters (see
        // ROL-1956) requests of this type are stored in salt.ignored.urls in
        // roller.properties
        if (httpReq.getMethod().equals("POST")
                && !isIgnoredURL(httpReq.getServletPath())) {

            String salt = httpReq.getParameter("salt");
            SaltCache saltCache = SaltCache.getInstance();
            if (salt == null || saltCache.get(salt) == null
                    || saltCache.get(salt).equals(false)) {

                if (log.isDebugEnabled()) {
                    log.debug("Salt value not found on POST to URL : "
                            + httpReq.getServletPath());
                }

                throw new ServletException("Security Violation");
            }
        }

        chain.doFilter(request, response);
    }

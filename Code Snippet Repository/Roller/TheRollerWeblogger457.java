    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        if (!initialized) {

            // first request, lets do our initialization
            HttpServletRequest request = (HttpServletRequest) req;
            // HttpServletResponse response = (HttpServletResponse) res;

            // determine absolute and relative url paths to the app
            String relPath = request.getContextPath();
            String absPath = this.getAbsoluteUrl(request);

            // set them in our config
            WebloggerRuntimeConfig.setAbsoluteContextURL(absPath);
            WebloggerRuntimeConfig.setRelativeContextURL(relPath);

            if (log.isDebugEnabled()) {
                log.debug("relPath = " + relPath);
                log.debug("absPath = " + absPath);
            }

            this.initialized = true;
        }

        chain.doFilter(req, res);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        log.debug("Entered "+request.getRequestURI());
        
        try {
            chain.doFilter(request, response);
        } finally {
            if (WebloggerFactory.isBootstrapped()) {
                log.debug("Releasing Roller Session");
                WebloggerFactory.getWeblogger().release();
            }
            
        }
        
        log.debug("Exiting "+request.getRequestURI());
    }

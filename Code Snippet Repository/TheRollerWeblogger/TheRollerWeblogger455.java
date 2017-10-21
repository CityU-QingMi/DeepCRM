    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        log.info("ENTERING "+request.getRequestURL());
        
        // some info about the request and response
        log.info("Response Object:");
        log.info("   isCommitted = "+response.isCommitted());
        log.info("   bufferSize  = "+response.getBufferSize());
        log.info("");
        
        chain.doFilter(request, response);
        
        log.info("EXITING "+request.getRequestURL());
        
        // some info about the request and response
        log.info("Response Object:");
        log.info("   isCommitted = "+response.isCommitted());
        log.info("   bufferSize  = "+response.getBufferSize());
        log.info("");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        log.debug("entering");
        
        // give each mapper a chance to handle the request
        for (RequestMapper mapper : requestMappers) {
            log.debug("trying mapper " + mapper.getClass().getName());

            boolean wasHandled = mapper.handleRequest(request, response);
            if(wasHandled) {
                // if mapper has handled the request then we are done
                log.debug("request handled by " + mapper.getClass().getName());
                log.debug("exiting");
                return;
            }
        }

        log.debug("request not mapped");
        
        // nobody handled the request, so let it continue as usual
        chain.doFilter(request, response);
        
        log.debug("exiting");
    }

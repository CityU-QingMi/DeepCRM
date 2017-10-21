    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        // check if client is allowed
        if(IPBanList.getInstance().isBanned(request.getRemoteAddr())) {
            log.debug("BANNED "+request.getRemoteAddr());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

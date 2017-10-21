    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        log.debug("Entered "+request.getRequestURI());
        
        if ("auto".equals(WebloggerConfig.getProperty("installation.type"))
                && !WebloggerFactory.isBootstrapped() 
                && !isInstallUrl(request.getRequestURI())) {
                    
            log.debug("Forwarding to install page");
            
            // we doing an install, so forward to installer
            RequestDispatcher rd = context.getRequestDispatcher(
                "/roller-ui/install/install.rol");
            rd.forward(req, res);
            
        } else {
            chain.doFilter(request, response);
        }
        
        log.debug("Exiting "+request.getRequestURI());
    }

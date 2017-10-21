    public WeblogResourceRequest(HttpServletRequest request) 
            throws InvalidRequestException {
        
        // let our parent take care of their business first
        // parent determines weblog handle and locale if specified
        super(request);
        
        // we only want the path info left over from after our parents parsing
        String pathInfo = this.getPathInfo();
        
        // parse the request object and figure out what we've got
        log.debug("parsing path "+pathInfo);
                
        
/**/
/**/
/**/
/**/
/**/
        if(pathInfo != null && pathInfo.trim().length() > 1) {
            
            this.resourcePath = pathInfo;
            if(pathInfo.startsWith("/")) {
                this.resourcePath = pathInfo.substring(1);
            }
            
            // Fix for ROL-1065: even though a + should mean space in a URL, folks
            // who upload files with plus signs expect them to work without
            // escaping. This is essentially what other systems do (e.g. JIRA) to
            // enable this.
            this.resourcePath = this.resourcePath.replaceAll("\\+", "%2B");
            
            // now we really decode the URL
            this.resourcePath = URLUtilities.decode(this.resourcePath);
        
        } else {
            throw new InvalidRequestException("invalid resource path info, "+
                    request.getRequestURL());
        }
        
        if(log.isDebugEnabled()) {
            log.debug("resourcePath = "+this.resourcePath);
        }
    }

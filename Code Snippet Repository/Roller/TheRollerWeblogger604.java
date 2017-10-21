    public WeblogMediaResourceRequest(HttpServletRequest request) 
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
        if (pathInfo != null && pathInfo.trim().length() > 1) {
            
            this.resourceId = pathInfo;
            if (pathInfo.startsWith("/")) {
                this.resourceId = pathInfo.substring(1);
            }
        
        } else {
            throw new InvalidRequestException("invalid resource path info, "+
                    request.getRequestURL());
        }

        if (request.getParameter("t") != null && "true".equals(request.getParameter("t"))) {
            thumbnail = true;
        }
        
        if(log.isDebugEnabled()) {
            log.debug("resourceId = "+this.resourceId);
        }
    }

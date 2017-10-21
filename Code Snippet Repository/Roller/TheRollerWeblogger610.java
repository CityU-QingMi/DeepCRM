    public WeblogPreviewResourceRequest(HttpServletRequest request) 
            throws InvalidRequestException {
        
        // let parent go first
        super(request);
        
        // all we need to worry about is the query params
        // the only param we expect is "theme"
        if(request.getParameter("theme") != null) {
            this.themeName = request.getParameter("theme");
        }
        
        if(log.isDebugEnabled()) {
            log.debug("theme = "+this.themeName);
        }
    }

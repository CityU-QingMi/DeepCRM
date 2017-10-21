    public WeblogPreviewRequest(HttpServletRequest request) 
            throws InvalidRequestException {
        
        // let parent go first
        super(request);
        
        // we may have a specific theme to preview
        if(request.getParameter("theme") != null) {
            this.themeName = request.getParameter("theme");
        }

        //we may need to know the type of page we are going to preview
         if(request.getParameter("type") != null) {
             this.setType(request.getParameter("type"));
         }
        
        // we may also have a specific entry to preview
        if(request.getParameter("previewEntry") != null) {
            this.previewEntry = URLUtilities.decode(request.getParameter("previewEntry"));
        }

        if(log.isDebugEnabled()) {
            log.debug("theme = "+this.themeName);
        }
    }

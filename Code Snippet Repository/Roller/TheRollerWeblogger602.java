    public PlanetRequest(HttpServletRequest request) throws InvalidRequestException {
        
        super(request);
        
        // parse the request object and figure out what we've got
        log.debug("parsing url "+request.getRequestURL());
        
        String servlet = request.getServletPath();
        
        // what servlet is our destination?
        if(servlet != null) {
            // strip off the leading slash
            servlet = servlet.substring(1);
            
            if(servlet.equals("planet.do")) {
                this.context = "planet";
                this.type = "page";
            } else if(servlet.equals("planetrss")) {
                this.context = "planet";
                this.type = "feed";
                this.flavor = "rss";
            } else {
                // not a request to a feed servlet
                throw new InvalidRequestException("not a planet request, "+request.getRequestURL());
            }
            
        } else {
            throw new InvalidRequestException("not a planet request, "+request.getRequestURL());
        }
        
        
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        if (request.getParameter("excerpts") != null) {
            this.excerpts = Boolean.valueOf(request.getParameter("excerpts"));
        }
        
        if (request.getParameter("group") != null) {
            this.group = request.getParameter("group");
        }
        
        
        // language is always from the browser
        language = request.getLocale().getLanguage();
    }

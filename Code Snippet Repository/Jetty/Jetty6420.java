        public void init(ClientUpgradeRequest request)
        {
            this.extensions = new ArrayList<>(request.getExtensions());
            this.subProtocols = new ArrayList<>(request.getSubProtocols());
    
            request.getHeaders().forEach((name, values) ->
                values.forEach((value) -> header(name, value))
            );
            
            for (HttpCookie cookie : request.getCookies())
            {
                cookie(cookie);
            }
        }

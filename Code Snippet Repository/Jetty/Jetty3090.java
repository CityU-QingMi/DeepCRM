        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            if (_newContextURL==null)
                return;

            String path=_newContextURL;
            if (!_discardPathInfo && request.getPathInfo()!=null)
                path=URIUtil.addPaths(path, request.getPathInfo());

            StringBuilder location = URIUtil.hasScheme(path)?new StringBuilder():baseRequest.getRootURL();

            location.append(path);
            if (!_discardQuery && request.getQueryString()!=null)
            {
                location.append('?');
                String q=request.getQueryString();
                q=q.replaceAll("\r\n?&=","!");
                location.append(q);
            }

            response.setHeader(HttpHeader.LOCATION.asString(),location.toString());

            if (_expires!=null)
                response.setHeader(HttpHeader.EXPIRES.asString(),_expires);

            response.setStatus(_permanent?HttpServletResponse.SC_MOVED_PERMANENTLY:HttpServletResponse.SC_FOUND);
            response.setContentLength(0);
            baseRequest.setHandled(true);
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String uriInContext)
        {
            // uriInContext is encoded, potentially with query

            if (uriInContext == null)
                return null;

            if (!uriInContext.startsWith("/"))
                return null;

            try
            {
                HttpURI uri = new HttpURI(null,null,0,uriInContext);

                String pathInfo=URIUtil.canonicalPath(uri.getDecodedPath());
                if (pathInfo==null)
                    return null;

                String contextPath=getContextPath();
                if (contextPath!=null && contextPath.length()>0)
                    uri.setPath(URIUtil.addPaths(contextPath,uri.getPath()));

                return new Dispatcher(ContextHandler.this,uri,pathInfo);
            }
            catch (Exception e)
            {
                LOG.ignore(e);
            }
            return null;
        }

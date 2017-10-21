        @Override
        public ServletContext getContext(String uripath)
        {
            ServletContext servletContext = super.getContext(uripath);

            if ( servletContext != null && _contextWhiteList != null )
            {
                for ( String context : _contextWhiteList )
                {
                    if ( context.equals(uripath) )
                    {
                        return servletContext;
                    }
                }

                return null;
            }
            else
            {
                return servletContext;
            }
        }

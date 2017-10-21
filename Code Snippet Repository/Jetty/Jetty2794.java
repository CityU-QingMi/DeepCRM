        @Override
        public Object getAttribute(String key)
        {
            if (Dispatcher.this._named==null)
            {
                if (key.equals(INCLUDE_PATH_INFO))    return _pathInfo;
                if (key.equals(INCLUDE_SERVLET_PATH)) return _servletPath;
                if (key.equals(INCLUDE_CONTEXT_PATH)) return _contextPath;
                if (key.equals(INCLUDE_QUERY_STRING)) return _query;
                if (key.equals(INCLUDE_REQUEST_URI))  return _requestURI;
            }
            else if (key.startsWith(__INCLUDE_PREFIX))
                    return null;


            return _attr.getAttribute(key);
        }

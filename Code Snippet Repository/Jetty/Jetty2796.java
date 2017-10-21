        @Override
        public void setAttribute(String key, Object value)
        {
            if (_named==null && key.startsWith("javax.servlet."))
            {
                if (key.equals(INCLUDE_PATH_INFO))         _pathInfo=(String)value;
                else if (key.equals(INCLUDE_REQUEST_URI))  _requestURI=(String)value;
                else if (key.equals(INCLUDE_SERVLET_PATH)) _servletPath=(String)value;
                else if (key.equals(INCLUDE_CONTEXT_PATH)) _contextPath=(String)value;
                else if (key.equals(INCLUDE_QUERY_STRING)) _query=(String)value;
                else if (value==null)
                    _attr.removeAttribute(key);
                else
                    _attr.setAttribute(key,value);
            }
            else if (value==null)
                _attr.removeAttribute(key);
            else
                _attr.setAttribute(key,value);
        }

        @Override
        public void setAttribute(String key, Object value)
        {
            if (_named==null && key.startsWith("javax.servlet."))
            {
                if (key.equals(FORWARD_PATH_INFO))
                    _pathInfo=(String)value;
                else if (key.equals(FORWARD_REQUEST_URI))
                    _requestURI=(String)value;
                else if (key.equals(FORWARD_SERVLET_PATH))
                    _servletPath=(String)value;
                else if (key.equals(FORWARD_CONTEXT_PATH))
                    _contextPath=(String)value;
                else if (key.equals(FORWARD_QUERY_STRING))
                    _query=(String)value;

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

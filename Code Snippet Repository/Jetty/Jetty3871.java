        public Object getAttribute(String name)
        {
            if (_included)
            {
                if (name.equals(Dispatcher.INCLUDE_REQUEST_URI))
                    return URIUtil.addPaths(URIUtil.addPaths(getContextPath(),_servletPath),_pathInfo);
                if (name.equals(Dispatcher.INCLUDE_PATH_INFO))
                    return _pathInfo;
                if (name.equals(Dispatcher.INCLUDE_SERVLET_PATH))
                    return _servletPath;
            }
            return super.getAttribute(name);
        }

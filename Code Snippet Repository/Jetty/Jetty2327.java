        protected void init(ServletConfig config) throws ServletException
        {
            _proxyTo = config.getInitParameter("proxyTo");
            if (_proxyTo == null)
                throw new UnavailableException("Init parameter 'proxyTo' is required.");

            String prefix = config.getInitParameter("prefix");
            if (prefix != null)
            {
                if (!prefix.startsWith("/"))
                    throw new UnavailableException("Init parameter 'prefix' must start with a '/'.");
                _prefix = prefix;
            }

            // Adjust prefix value to account for context path
            String contextPath = config.getServletContext().getContextPath();
            _prefix = _prefix == null ? contextPath : (contextPath + _prefix);

            if (proxyServlet._log.isDebugEnabled())
                proxyServlet._log.debug(config.getServletName() + " @ " + _prefix + " to " + _proxyTo);
        }

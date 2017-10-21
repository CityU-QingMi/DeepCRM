        public ContextHandler createContextHandler()
        throws Exception
        {
            if (_webApp != null)
            {
                configureWebApp();
                return _webApp;
            }
            
            createWebApp();
            return _webApp;
        }

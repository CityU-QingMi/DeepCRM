        private DelegatingContentProvider(HttpServletRequest clientRequest, Request proxyRequest, HttpServletResponse proxyResponse, ContentProvider provider, DeferredContentProvider deferred)
        {
            this.clientRequest = clientRequest;
            this.proxyRequest = proxyRequest;
            this.proxyResponse = proxyResponse;
            this.iterator = provider.iterator();
            this.deferred = deferred;
            if (provider instanceof AsyncContentProvider)
                ((AsyncContentProvider)provider).setListener(this);
        }

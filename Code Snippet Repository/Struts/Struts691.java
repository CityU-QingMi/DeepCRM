    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            String uri = RequestUtils.getUri(request);
            if (excludedPatterns != null && prepare.isUrlExcluded(request, excludedPatterns)) {
                LOG.trace("Request {} is excluded from handling by Struts, passing request to other filters", uri);
                chain.doFilter(request, response);
            } else {
                LOG.trace("Checking if {} is a static resource", uri);
                boolean handled = execute.executeStaticResourceRequest(request, response);
                if (!handled) {
                    LOG.trace("Assuming uri {} as a normal action", uri);
                    prepare.setEncodingAndLocale(request, response);
                    prepare.createActionContext(request, response);
                    prepare.assignDispatcherToThread();
                    request = prepare.wrapRequest(request);
                    ActionMapping mapping = prepare.findActionMapping(request, response, true);
                    if (mapping == null) {
                        LOG.trace("Cannot find mapping for {}, passing to other filters", uri);
                        chain.doFilter(request, response);
                    } else {
                        LOG.trace("Found mapping {} for {}", mapping, uri);
                        execute.executeAction(request, response, mapping);
                    }
                }
            }
        } finally {
            prepare.cleanupRequest(request);
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (excludeUrl(request)) {
            chain.doFilter(request, response);
            return;
        }

        // This is necessary since we need the dispatcher instance, which was created by the prepare filter
        if (execute == null) {
            lazyInit();
        }

        ActionMapping mapping = prepare.findActionMapping(request, response);

        //if recursion counter is > 1, it means we are in a "forward", in that case a mapping will still be
        //in the request, if we handle it, it will lead to an infinite loop, see WW-3077
        Integer recursionCounter = (Integer) request.getAttribute(PrepareOperations.CLEANUP_RECURSION_COUNTER);

        if (mapping == null || recursionCounter > 1) {
            boolean handled = execute.executeStaticResourceRequest(request, response);
            if (!handled) {
                chain.doFilter(request, response);
            }
        } else {
            execute.executeAction(request, response, mapping);
        }
    }

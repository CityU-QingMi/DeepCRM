    public void setUp() {
        filterPrepare = new StrutsPrepareFilter();
        filterExecute = new StrutsExecuteFilter();
        failFilter = new Filter() {
            public void init(FilterConfig filterConfig) throws ServletException {}
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                fail("Should never get here");
            }
            public void destroy() {}
        };
        stringFilter = new Filter() {
            public void init(FilterConfig filterConfig) throws ServletException {}
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                response.getWriter().write("content");
                assertNotNull(ActionContext.getContext());
                assertNotNull(Dispatcher.getInstance());
            }
            public void destroy() {}
        };
    }

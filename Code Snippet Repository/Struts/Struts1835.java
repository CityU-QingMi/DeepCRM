    public void testFilterInMiddle() throws ServletException, IOException {
        Filter middle = new Filter() {
            public void init(FilterConfig filterConfig) throws ServletException {}
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                assertNotNull(ActionContext.getContext());
                assertNotNull(Dispatcher.getInstance());
                assertNull(ActionContext.getContext().getActionInvocation());
                chain.doFilter(request, response);
                assertEquals("hello", ActionContext.getContext().getActionInvocation().getProxy().getActionName());
            }
            public void destroy() {}
        };
        MockHttpServletResponse response = run("/hello.action", filterPrepare, middle, filterExecute, failFilter);
        assertEquals(200, response.getStatus());
    }

    public void test200() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterConfig filterConfig = new MockFilterConfig();
        MockFilterChain filterChain = new MockFilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                fail("Shouldn't get here");
            }
        };

        request.setRequestURI("/hello.action");
        StrutsPrepareAndExecuteFilter filter = new StrutsPrepareAndExecuteFilter();
        filter.init(filterConfig);
        filter.doFilter(request, response, filterChain);
        assertEquals(200, response.getStatus());
        assertNull(ActionContext.getContext());
        assertNull(Dispatcher.getInstance());
    }

    public void testActionMappingLookup() throws ServletException, IOException {
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

        //simulate a FORWARD
        MockFilterChain filterChain2 = new MockFilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                req.setAttribute("__invoked", true);
            }
        };
        request.setRequestURI("hello.jsp");
        filter.doFilter(request, response, filterChain2);
        assertEquals(200, response.getStatus());
        assertNull(ActionContext.getContext());
        assertNull(Dispatcher.getInstance());
        assertTrue((Boolean) request.getAttribute("__invoked"));
    }

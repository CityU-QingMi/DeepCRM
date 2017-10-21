    public void testStaticFallthrough() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterConfig filterConfig = new MockFilterConfig();
        MockFilterChain filterChain = new MockFilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                assertNotNull(ActionContext.getContext());
                assertNotNull(Dispatcher.getInstance());
                try {
                    res.getWriter().write("found");
                } catch (IOException e) {
                    fail(e.getMessage());
                }
            }
        };

        request.setRequestURI("/foo.txt");
        StrutsPrepareAndExecuteFilter filter = new StrutsPrepareAndExecuteFilter();
        filter.init(filterConfig);
        filter.doFilter(request, response, filterChain);
        assertEquals(200, response.getStatus());
        assertEquals("found", response.getContentAsString());
        assertNull(ActionContext.getContext());
        assertNull(Dispatcher.getInstance());
    }

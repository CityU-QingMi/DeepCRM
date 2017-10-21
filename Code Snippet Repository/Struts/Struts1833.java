    public void testStaticExecute() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterConfig filterConfig = new MockFilterConfig();
        MockFilterChain filterChain = new MockFilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                fail("Should never get here");
            }
        };

        request.setRequestURI("/struts/utils.js");
        StrutsPrepareAndExecuteFilter filter = new StrutsPrepareAndExecuteFilter();
        filter.init(filterConfig);
        filter.doFilter(request, response, filterChain);
        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("StrutsUtils"));
        assertNull(ActionContext.getContext());
        assertNull(Dispatcher.getInstance());
    }

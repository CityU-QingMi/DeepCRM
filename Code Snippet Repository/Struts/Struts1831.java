    public void testUriPatternExclusion() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterConfig filterConfig = new MockFilterConfig();
        MockFilterChain filterChain = new MockFilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                req.setAttribute("i_was", "invoked");
            }
        };

        request.setRequestURI("/hello.action");
        StrutsPrepareAndExecuteFilter filter = new StrutsPrepareAndExecuteFilter() {
            @Override
            public void init( FilterConfig filterConfig ) throws ServletException {
                super.init(filterConfig);
                excludedPatterns = new ArrayList<Pattern>();
                excludedPatterns.add(Pattern.compile(".*hello.*"));
            }
        };
        filter.init(filterConfig);
        filter.doFilter(request, response, filterChain);
        assertEquals(200, response.getStatus());
        assertEquals("invoked", request.getAttribute("i_was"));
    }

    private MockHttpServletResponse run(String uri, ActionContext existingContext, final Filter... filters) throws ServletException, IOException {
        final LinkedList<Filter> filterList = new LinkedList<Filter>(Arrays.asList(filters));
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterConfig filterConfig = new MockFilterConfig();
        MockFilterChain filterChain = new MockFilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                Filter next = (filterList.size() > 0 ? filterList.removeFirst() : null);
                if (next != null) {
                    try {
                        next.doFilter(req, res, this);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        if (existingContext != null) {
            request.setAttribute(PrepareOperations.CLEANUP_RECURSION_COUNTER, 1);
        }
        request.setRequestURI(uri);
        for (Filter filter : filters) {
            filter.init(filterConfig);
        }

        ActionContext.setContext(existingContext);
        filterList.removeFirst().doFilter(request, response, filterChain);
        if (existingContext == null) {
            assertNull(ActionContext.getContext());
            assertNull(Dispatcher.getInstance());
        } else {
            assertEquals(Integer.valueOf(1), request.getAttribute(PrepareOperations.CLEANUP_RECURSION_COUNTER));
        }
        return response;
    }

    @Test
    public void testDoFilterFirstTime() throws Exception {
        this.filter.init(this.filterConfig);

        then(initializer).should().clearLoggerContext();
        reset(initializer);

        given(request.getAttribute(Log4jServletFilter.ALREADY_FILTERED_ATTRIBUTE)).willReturn(null);

        this.filter.doFilter(request, response, chain);

        then(request).should().setAttribute(eq(Log4jServletFilter.ALREADY_FILTERED_ATTRIBUTE), eq(true));
        then(initializer).should().setLoggerContext();
        then(chain).should().doFilter(same(request), same(response));
        then(chain).shouldHaveNoMoreInteractions();
        then(initializer).should().clearLoggerContext();
    }

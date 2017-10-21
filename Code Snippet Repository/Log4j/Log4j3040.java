    @Test
    public void testDoFilterSecondTime() throws Exception {
        this.filter.init(this.filterConfig);

        then(initializer).should().clearLoggerContext();

        given(request.getAttribute(Log4jServletFilter.ALREADY_FILTERED_ATTRIBUTE)).willReturn(true);

        this.filter.doFilter(request, response, chain);

        then(chain).should().doFilter(same(request), same(response));
        then(chain).shouldHaveNoMoreInteractions();
    }

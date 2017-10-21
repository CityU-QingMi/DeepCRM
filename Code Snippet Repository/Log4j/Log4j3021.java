    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        if (request.getAttribute(ALREADY_FILTERED_ATTRIBUTE) != null) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute(ALREADY_FILTERED_ATTRIBUTE, Boolean.TRUE);

            try {
                this.initializer.setLoggerContext();

                chain.doFilter(request, response);
            } finally {
                this.initializer.clearLoggerContext();
            }
        }
    }

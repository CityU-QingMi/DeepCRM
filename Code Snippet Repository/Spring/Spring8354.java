	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestPath = urlPathHelper.getPathWithinApplication(httpRequest);

		if (matches(requestPath)) {
			this.delegate.doFilter(request, response, filterChain);
		}
		else {
			filterChain.doFilter(request, response);
		}
	}

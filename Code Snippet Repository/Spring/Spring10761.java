	private String sendRedirect(final String location) throws ServletException, IOException {
		MockHttpServletResponse response = doWithFiltersAndGetResponse(this.filter, new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				response.sendRedirect(location);
			}
		});
		return response.getRedirectedUrl();
	}

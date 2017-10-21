	@Override
	protected void doFilterInternal(final HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		if (("PUT".equals(request.getMethod()) || "PATCH".equals(request.getMethod())) && isFormContentType(request)) {
			HttpInputMessage inputMessage = new ServletServerHttpRequest(request) {
				@Override
				public InputStream getBody() throws IOException {
					return request.getInputStream();
				}
			};
			MultiValueMap<String, String> formParameters = this.formConverter.read(null, inputMessage);
			if (!formParameters.isEmpty()) {
				HttpServletRequest wrapper = new HttpPutFormContentRequestWrapper(request, formParameters);
				filterChain.doFilter(wrapper, response);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

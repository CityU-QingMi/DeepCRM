		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String uri = httpRequest.getRequestURI();
			HttpHeaders headers = new ServletServerHttpRequest(httpRequest).getHeaders();
			this.requests.put(uri, headers);

			for (String suffix : this.sleepDelayMap.keySet()) {
				if ((httpRequest).getRequestURI().endsWith(suffix)) {
					try {
						Thread.sleep(this.sleepDelayMap.get(suffix));
						break;
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			for (String suffix : this.sendErrorMap.keySet()) {
				if ((httpRequest).getRequestURI().endsWith(suffix)) {
					((HttpServletResponse) response).sendError(this.sendErrorMap.get(suffix));
					return;
				}
			}
			chain.doFilter(request, response);
		}

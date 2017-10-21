		public ForwardedHeaderExtractingRequest(HttpServletRequest request, UrlPathHelper pathHelper) {
			super(request);

			HttpRequest httpRequest = new ServletServerHttpRequest(request);
			UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(httpRequest).build();
			int port = uriComponents.getPort();

			this.scheme = uriComponents.getScheme();
			this.secure = "https".equals(scheme);
			this.host = uriComponents.getHost();
			this.port = (port == -1 ? (this.secure ? 443 : 80) : port);

			String prefix = getForwardedPrefix(request);
			this.contextPath = (prefix != null ? prefix : request.getContextPath());
			this.requestUri = this.contextPath + pathHelper.getPathWithinApplication(request);
			this.requestUrl = this.scheme + "://" + this.host + (port == -1 ? "" : ":" + port) + this.requestUri;
		}

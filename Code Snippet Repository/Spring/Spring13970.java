	protected boolean checkOrigin(ServerHttpRequest request, ServerHttpResponse response, HttpMethod... httpMethods)
			throws IOException {

		if (WebUtils.isSameOrigin(request)) {
			return true;
		}

		if (!WebUtils.isValidOrigin(request, this.allowedOrigins)) {
			if (logger.isWarnEnabled()) {
				logger.warn("Origin header value '" + request.getHeaders().getOrigin() + "' not allowed.");
			}
			response.setStatusCode(HttpStatus.FORBIDDEN);
			return false;
		}

		return true;
	}

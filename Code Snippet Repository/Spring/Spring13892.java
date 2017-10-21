	private RequestContext createRequestContext(HttpServletRequest request, String endpointPath, HttpHeaders headers) {
		RequestContext context =
				RequestContext.Builder.create()
						.requestURI(URI.create(endpointPath))
						.userPrincipal(request.getUserPrincipal())
						.secure(request.isSecure())
						.remoteAddr(request.getRemoteAddr())
						.build();
		for (String header : headers.keySet()) {
			context.getHeaders().put(header, headers.get(header));
		}
		return context;
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws IOException {
		// Start async before Read/WriteListener registration
		AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(-1);

		ServerHttpRequest httpRequest = createRequest(((HttpServletRequest) request), asyncContext);
		ServerHttpResponse httpResponse = createResponse(((HttpServletResponse) response), asyncContext);

		if (HttpMethod.HEAD.equals(httpRequest.getMethod())) {
			httpResponse = new HttpHeadResponseDecorator(httpResponse);
		}

		asyncContext.addListener(ERROR_LISTENER);

		HandlerResultSubscriber subscriber = new HandlerResultSubscriber(asyncContext);
		this.httpHandler.handle(httpRequest, httpResponse).subscribe(subscriber);
	}

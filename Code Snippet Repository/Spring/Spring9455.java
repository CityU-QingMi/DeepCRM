	public ServletServerHttpRequest(HttpServletRequest request, AsyncContext asyncContext,
			DataBufferFactory bufferFactory, int bufferSize) throws IOException {

		super(initUri(request), request.getContextPath(), initHeaders(request));

		Assert.notNull(bufferFactory, "'bufferFactory' must not be null");
		Assert.isTrue(bufferSize > 0, "'bufferSize' must be higher than 0");

		this.request = request;
		this.bufferFactory = bufferFactory;
		this.buffer = new byte[bufferSize];

		asyncContext.addListener(new RequestAsyncListener());

		// Tomcat expects ReadListener registration on initial thread
		ServletInputStream inputStream = request.getInputStream();
		this.bodyPublisher = new RequestBodyPublisher(inputStream);
		this.bodyPublisher.registerReadListener();
	}

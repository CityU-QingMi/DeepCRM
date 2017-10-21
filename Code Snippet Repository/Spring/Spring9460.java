	public ServletServerHttpResponse(HttpServletResponse response, AsyncContext asyncContext,
			DataBufferFactory bufferFactory, int bufferSize) throws IOException {

		super(bufferFactory);

		Assert.notNull(response, "HttpServletResponse must not be null");
		Assert.notNull(bufferFactory, "DataBufferFactory must not be null");
		Assert.isTrue(bufferSize > 0, "Buffer size must be greater than 0");

		this.response = response;
		this.outputStream = response.getOutputStream();
		this.bufferSize = bufferSize;

		asyncContext.addListener(new ResponseAsyncListener());

		// Tomcat expects WriteListener registration on initial thread
		response.getOutputStream().setWriteListener(new ResponseBodyWriteListener());
	}

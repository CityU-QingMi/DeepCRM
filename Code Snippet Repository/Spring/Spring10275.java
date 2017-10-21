	public RxNettyServerHttpRequest(HttpServerRequest<ByteBuf> request,
			NettyDataBufferFactory dataBufferFactory, InetSocketAddress remoteAddress)
			throws URISyntaxException {

		super(initUri(request, remoteAddress), "", initHeaders(request));
		this.request = request;

		Assert.notNull(dataBufferFactory, "NettyDataBufferFactory must not be null");
		this.dataBufferFactory = dataBufferFactory;

		this.remoteAddress = remoteAddress;
	}

	@Parameters(name = "")
	public static Object[][] arguments() throws IOException {

		Flux<? extends WebSocketClient> clients = Flux.concat(
				Flux.just(new TomcatWebSocketClient(new WsWebSocketContainer())).repeat(5),
				Flux.just(new JettyWebSocketClient()).repeat(5),
				Flux.just(new ReactorNettyWebSocketClient()).repeat(5),
				Flux.just(new RxNettyWebSocketClient()).repeat(5),
				Flux.just(new UndertowWebSocketClient(Xnio.getInstance().createWorker(OptionMap.EMPTY))).repeat(5));

		Flux<? extends HttpServer> servers = Flux.just(
				new TomcatHttpServer(TMP_DIR.getAbsolutePath(), WsContextListener.class),
				new JettyHttpServer(),
				new ReactorHttpServer(),
				new RxNettyHttpServer(),
				new UndertowHttpServer()).repeat(5);

		Flux<? extends Class<?>> configs = Flux.just(
				TomcatConfig.class,
				JettyConfig.class,
				ReactorNettyConfig.class,
				RxNettyConfig.class,
				UndertowConfig.class).repeat(5);

		return Flux.zip(clients, servers, configs)
				.map(Tuple3::toArray)
				.collectList()
				.block()
				.toArray(new Object[25][2]);
	}

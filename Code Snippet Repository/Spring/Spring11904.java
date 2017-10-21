	@Before
	public void setup() throws Exception {
		// TODO
		// Caused by: java.io.IOException: Upgrade responses cannot have a transfer coding
		// at org.xnio.http.HttpUpgrade$HttpUpgradeState.handleUpgrade(HttpUpgrade.java:490)
		// at org.xnio.http.HttpUpgrade$HttpUpgradeState.access$1200(HttpUpgrade.java:165)
		// at org.xnio.http.HttpUpgrade$HttpUpgradeState$UpgradeResultListener.handleEvent(HttpUpgrade.java:461)
		// at org.xnio.http.HttpUpgrade$HttpUpgradeState$UpgradeResultListener.handleEvent(HttpUpgrade.java:400)
		// at org.xnio.ChannelListeners.invokeChannelListener(ChannelListeners.java:92)

		assumeFalse(this.client instanceof UndertowWebSocketClient && this.server instanceof RxNettyHttpServer);

		this.server.setHandler(createHttpHandler());
		this.server.afterPropertiesSet();
		this.server.start();

		// Set dynamically chosen port
		this.port = this.server.getPort();

		if (this.client instanceof Lifecycle) {
			((Lifecycle) this.client).start();
		}
	}

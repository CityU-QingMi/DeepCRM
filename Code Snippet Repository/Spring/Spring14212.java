	@Before
	@SuppressWarnings("")
	public void setup() {
		this.infoReceiver = mock(InfoReceiver.class);
		this.webSocketTransport = new TestTransport("WebSocketTestTransport");
		this.xhrTransport = new XhrTestTransport("XhrTestTransport");

		List<Transport> transports = new ArrayList<>();
		transports.add(this.webSocketTransport);
		transports.add(this.xhrTransport);
		this.sockJsClient = new SockJsClient(transports);
		this.sockJsClient.setInfoReceiver(this.infoReceiver);

		this.connectCallback = mock(ListenableFutureCallback.class);
	}

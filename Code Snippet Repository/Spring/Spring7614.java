	@Before
	public void setup() {

		this.outboundChannel = new StubMessageChannel();

		this.brokerRelay = new StompBrokerRelayMessageHandler(new StubMessageChannel(),
				this.outboundChannel, new StubMessageChannel(), Arrays.asList("/topic")) {

			@Override
			protected void startInternal() {
				publishBrokerAvailableEvent(); // Force this, since we'll never actually connect
				super.startInternal();
			}
		};

		this.tcpClient = new StubTcpOperations();
		this.brokerRelay.setTcpClient(this.tcpClient);
	}

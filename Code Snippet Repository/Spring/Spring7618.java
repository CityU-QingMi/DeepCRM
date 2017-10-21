	@Test
	public void loginAndPasscode() throws Exception {

		this.brokerRelay.setSystemLogin("syslogin");
		this.brokerRelay.setSystemPasscode("syspasscode");
		this.brokerRelay.setClientLogin("clientlogin");
		this.brokerRelay.setClientPasscode("clientpasscode");

		this.brokerRelay.start();
		this.brokerRelay.handleMessage(connectMessage("sess1", "joe"));

		assertEquals(2, this.tcpClient.getSentMessages().size());

		StompHeaderAccessor headers1 = this.tcpClient.getSentHeaders(0);
		assertEquals(StompCommand.CONNECT, headers1.getCommand());
		assertEquals("syslogin", headers1.getLogin());
		assertEquals("syspasscode", headers1.getPasscode());

		StompHeaderAccessor headers2 = this.tcpClient.getSentHeaders(1);
		assertEquals(StompCommand.CONNECT, headers2.getCommand());
		assertEquals("clientlogin", headers2.getLogin());
		assertEquals("clientpasscode", headers2.getPasscode());
	}

	@Test
	@SuppressWarnings("")
	public void checkSession() throws Exception {
		TestWebSocketSession session1 = new TestWebSocketSession("id1");
		TestWebSocketSession session2 = new TestWebSocketSession("id2");
		session1.setOpen(true);
		session2.setOpen(true);
		session1.setAcceptedProtocol("v12.stomp");
		session2.setAcceptedProtocol("v12.stomp");

		this.webSocketHandler.setProtocolHandlers(Arrays.asList(this.stompHandler));
		this.webSocketHandler.afterConnectionEstablished(session1);
		this.webSocketHandler.afterConnectionEstablished(session2);

		DirectFieldAccessor handlerAccessor = new DirectFieldAccessor(this.webSocketHandler);
		Map<String, ?> map = (Map<String, ?>) handlerAccessor.getPropertyValue("sessions");
		DirectFieldAccessor session1Accessor = new DirectFieldAccessor(map.get("id1"));
		DirectFieldAccessor session2Accessor = new DirectFieldAccessor(map.get("id2"));

		long sixtyOneSecondsAgo = System.currentTimeMillis() - 61 * 1000;
		handlerAccessor.setPropertyValue("lastSessionCheckTime", sixtyOneSecondsAgo);
		session1Accessor.setPropertyValue("createTime", sixtyOneSecondsAgo);
		session2Accessor.setPropertyValue("createTime", sixtyOneSecondsAgo);

		this.webSocketHandler.start();
		this.webSocketHandler.handleMessage(session1, new TextMessage("foo"));

		assertTrue(session1.isOpen());
		assertNull(session1.getCloseStatus());

		assertFalse(session2.isOpen());
		assertEquals(CloseStatus.SESSION_NOT_RELIABLE, session2.getCloseStatus());

		assertNotEquals("lastSessionCheckTime not updated", sixtyOneSecondsAgo,
				handlerAccessor.getPropertyValue("lastSessionCheckTime"));
	}

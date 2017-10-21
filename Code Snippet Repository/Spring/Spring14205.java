	@Test
	public void connectReceiveAndClose() throws Exception {
		String body = "o\n" + "a[\"foo\"]\n" + "c[3000,\"Go away!\"]";
		ClientHttpResponse response = response(HttpStatus.OK, body);
		connect(response);

		verify(this.webSocketHandler).afterConnectionEstablished(any());
		verify(this.webSocketHandler).handleMessage(any(), eq(new TextMessage("foo")));
		verify(this.webSocketHandler).afterConnectionClosed(any(), eq(new CloseStatus(3000, "Go away!")));
		verifyNoMoreInteractions(this.webSocketHandler);
	}

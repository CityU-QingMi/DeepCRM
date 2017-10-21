	@Test
	public void connectReceiveAndCloseWithPrelude() throws Exception {
		StringBuilder sb = new StringBuilder(2048);
		for (int i = 0; i < 2048; i++) {
			sb.append('h');
		}
		String body = sb.toString() + "\n" + "o\n" + "a[\"foo\"]\n" + "c[3000,\"Go away!\"]";
		ClientHttpResponse response = response(HttpStatus.OK, body);
		connect(response);

		verify(this.webSocketHandler).afterConnectionEstablished(any());
		verify(this.webSocketHandler).handleMessage(any(), eq(new TextMessage("foo")));
		verify(this.webSocketHandler).afterConnectionClosed(any(), eq(new CloseStatus(3000, "Go away!")));
		verifyNoMoreInteractions(this.webSocketHandler);
	}

	@Test
	public void responseClosedAfterDisconnected() throws Exception {
		String body = "o\n" + "c[3000,\"Go away!\"]\n" + "a[\"foo\"]\n";
		ClientHttpResponse response = response(HttpStatus.OK, body);
		connect(response);

		verify(this.webSocketHandler).afterConnectionEstablished(any());
		verify(this.webSocketHandler).afterConnectionClosed(any(), any());
		verifyNoMoreInteractions(this.webSocketHandler);
		verify(response).close();
	}

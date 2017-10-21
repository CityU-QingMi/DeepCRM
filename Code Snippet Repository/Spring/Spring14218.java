	@Test
	public void sendMessage() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("foo", "bar");
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		TestXhrTransport transport = new TestXhrTransport();
		transport.sendMessageResponseToReturn = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		URI url = new URI("http://example.com");
		transport.executeSendRequest(url, requestHeaders, new TextMessage("payload"));
		assertEquals(2, transport.actualSendRequestHeaders.size());
		assertEquals("bar", transport.actualSendRequestHeaders.getFirst("foo"));
		assertEquals(MediaType.APPLICATION_JSON, transport.actualSendRequestHeaders.getContentType());
	}

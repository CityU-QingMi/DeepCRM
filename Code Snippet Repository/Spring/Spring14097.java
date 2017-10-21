	@Test
	public void afterConnectionEstablished() throws Exception {

		willThrow(new IllegalStateException("error"))
			.given(this.delegate).afterConnectionEstablished(this.session);

		this.decorator.afterConnectionEstablished(this.session);

		assertEquals(CloseStatus.SERVER_ERROR, this.session.getCloseStatus());
	}

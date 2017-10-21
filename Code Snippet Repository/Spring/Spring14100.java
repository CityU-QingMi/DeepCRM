	@Test
	public void afterConnectionClosed() throws Exception {

		CloseStatus closeStatus = CloseStatus.NORMAL;

		willThrow(new IllegalStateException("error"))
			.given(this.delegate).afterConnectionClosed(this.session, closeStatus);

		this.decorator.afterConnectionClosed(this.session, closeStatus);

		assertNull(this.session.getCloseStatus());
	}

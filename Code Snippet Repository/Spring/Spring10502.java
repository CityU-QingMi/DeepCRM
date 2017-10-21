	@Test
	public void hasError() throws Exception {
		given(this.response.getRawStatusCode()).willReturn(HttpStatus.I_AM_A_TEAPOT.value());
		assertTrue(this.errorHandler.hasError(this.response));

		given(this.response.getRawStatusCode()).willReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
		assertTrue(this.errorHandler.hasError(this.response));

		given(this.response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		assertFalse(this.errorHandler.hasError(this.response));
	}

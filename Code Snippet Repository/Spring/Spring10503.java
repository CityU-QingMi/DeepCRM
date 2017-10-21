	@Test
	public void hasErrorOverride() throws Exception {
		this.errorHandler.setSeriesMapping(Collections
				.singletonMap(HttpStatus.Series.CLIENT_ERROR, null));

		given(this.response.getRawStatusCode()).willReturn(HttpStatus.I_AM_A_TEAPOT.value());
		assertTrue(this.errorHandler.hasError(this.response));

		given(this.response.getRawStatusCode()).willReturn(HttpStatus.NOT_FOUND.value());
		assertFalse(this.errorHandler.hasError(this.response));

		given(this.response.getRawStatusCode()).willReturn(HttpStatus.OK.value());
		assertFalse(this.errorHandler.hasError(this.response));
	}

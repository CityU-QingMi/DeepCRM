	@Test
	public void isConcurrentHandlingStarted() {
		given(this.asyncWebRequest.isAsyncStarted()).willReturn(false);

		assertFalse(this.asyncManager.isConcurrentHandlingStarted());

		reset(this.asyncWebRequest);
		given(this.asyncWebRequest.isAsyncStarted()).willReturn(true);

		assertTrue(this.asyncManager.isConcurrentHandlingStarted());
	}

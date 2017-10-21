	@Test
	public void exceptionDuringPreHandle() throws Exception {
		Exception ex = new Exception("");

		given(this.interceptor1.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor2.preHandle(this.request, this.response, this.handler)).willThrow(ex);

		try {
			this.chain.applyPreHandle(request, response);
		}
		catch (Exception actual) {
			assertSame(ex, actual);
		}
		this.chain.triggerAfterCompletion(this.request, this.response, ex);

		verify(this.interceptor1).afterCompletion(this.request, this.response, this.handler, ex);
		verify(this.interceptor3, never()).preHandle(this.request, this.response, this.handler);
	}

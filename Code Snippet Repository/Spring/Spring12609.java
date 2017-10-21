	@Test
	public void exceptionAfterPreHandle() throws Exception {
		Exception ex = new Exception("");

		given(this.interceptor1.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor2.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor3.preHandle(this.request, this.response, this.handler)).willReturn(true);

		this.chain.applyPreHandle(request, response);
		this.chain.triggerAfterCompletion(this.request, this.response, ex);

		verify(this.interceptor3).afterCompletion(this.request, this.response, this.handler, ex);
		verify(this.interceptor2).afterCompletion(this.request, this.response, this.handler, ex);
		verify(this.interceptor1).afterCompletion(this.request, this.response, this.handler, ex);
	}

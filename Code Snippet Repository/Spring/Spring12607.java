	@Test
	public void successAsyncScenario() throws Exception {
		given(this.interceptor1.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor2.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor3.preHandle(this.request, this.response, this.handler)).willReturn(true);

		this.chain.applyPreHandle(request, response);
		this.chain.applyAfterConcurrentHandlingStarted(request, response);
		this.chain.triggerAfterCompletion(this.request, this.response, null);

		verify(this.interceptor1).afterConcurrentHandlingStarted(request, response, this.handler);
		verify(this.interceptor2).afterConcurrentHandlingStarted(request, response, this.handler);
		verify(this.interceptor3).afterConcurrentHandlingStarted(request, response, this.handler);
	}

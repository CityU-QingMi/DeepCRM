	@Test
	public void successScenario() throws Exception {
		ModelAndView mav = new ModelAndView();

		given(this.interceptor1.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor2.preHandle(this.request, this.response, this.handler)).willReturn(true);
		given(this.interceptor3.preHandle(this.request, this.response, this.handler)).willReturn(true);

		this.chain.applyPreHandle(request, response);
		this.chain.applyPostHandle(request, response, mav);
		this.chain.triggerAfterCompletion(this.request, this.response, null);

		verify(this.interceptor1).postHandle(this.request, this.response, this.handler, mav);
		verify(this.interceptor2).postHandle(this.request, this.response, this.handler, mav);
		verify(this.interceptor3).postHandle(this.request, this.response, this.handler, mav);

		verify(this.interceptor3).afterCompletion(this.request, this.response, this.handler, null);
		verify(this.interceptor2).afterCompletion(this.request, this.response, this.handler, null);
		verify(this.interceptor1).afterCompletion(this.request, this.response, this.handler, null);
	}

	public StubMvcResult(MockHttpServletRequest request,
						 Object handler,
						 HandlerInterceptor[] interceptors,
						 Exception resolvedException,
						 ModelAndView mav,
						 FlashMap flashMap,
						 MockHttpServletResponse response) {
		this.request = request;
		this.handler = handler;
		this.interceptors = interceptors;
		this.resolvedException = resolvedException;
		this.mav = mav;
		this.flashMap = flashMap;
		this.response = response;
	}

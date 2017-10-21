	private HandlerExecutionChain getHandler(MockHttpServletRequest req) throws Exception {
		HandlerExecutionChain hec = hm.getHandler(req);
		HandlerInterceptor[] interceptors = hec.getInterceptors();
		if (interceptors != null) {
			for (HandlerInterceptor interceptor : interceptors) {
				interceptor.preHandle(req, null, hec.getHandler());
			}
		}
		return hec;
	}

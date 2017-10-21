	public static RequestBuilder asyncDispatch(final MvcResult mvcResult) {

		// There must be an async result before dispatching
		mvcResult.getAsyncResult();

		return new RequestBuilder() {
			@Override
			public MockHttpServletRequest buildRequest(ServletContext servletContext) {
				MockHttpServletRequest request = mvcResult.getRequest();
				request.setDispatcherType(DispatcherType.ASYNC);
				request.setAsyncStarted(false);
				return request;
			}
		};
	}

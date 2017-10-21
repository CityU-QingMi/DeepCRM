	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		restActionInvocation = new RestActionInvocationTester();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		ServletActionContext.setRequest(request);
		ServletActionContext.setResponse(response);

	}

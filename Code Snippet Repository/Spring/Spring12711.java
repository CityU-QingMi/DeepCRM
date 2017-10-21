	@Before
	public void setUp() throws Exception {
		exceptionResolver = new SimpleMappingExceptionResolver();
		handler1 = new String();
		handler2 = new Object();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setMethod("GET");
		genericException = new Exception();
	}

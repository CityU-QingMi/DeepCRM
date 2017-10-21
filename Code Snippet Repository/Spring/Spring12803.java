	@Before
	public void setup() throws Exception {
		TestController testController = new TestController();

		this.fooMethod = new HandlerMethod(testController, "foo");
		this.fooParamMethod = new HandlerMethod(testController, "fooParam");
		this.barMethod = new HandlerMethod(testController, "bar");
		this.emptyMethod = new HandlerMethod(testController, "empty");

		this.handlerMapping = new TestRequestMappingInfoHandlerMapping();
		this.handlerMapping.registerHandler(testController);
		this.handlerMapping.setRemoveSemicolonContent(false);
	}

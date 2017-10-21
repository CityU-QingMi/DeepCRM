	@Before
	public void setUp() throws Exception {

		this.integerType = new MethodParameter(getClass().getDeclaredMethod("handleInteger"), -1);
		this.stringType = new MethodParameter(getClass().getDeclaredMethod("handleString"), -1);

		this.integerHandler = mock(HandlerMethodReturnValueHandler.class);
		when(this.integerHandler.supportsReturnType(this.integerType)).thenReturn(true);

		this.handlers = new HandlerMethodReturnValueHandlerComposite();
		this.handlers.addHandler(this.integerHandler);

		mavContainer = new ModelAndViewContainer();
	}

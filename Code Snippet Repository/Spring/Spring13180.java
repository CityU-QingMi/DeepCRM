	@Before
	public void setup() throws Exception {
		processor = new ServletModelAttributeMethodProcessor(false);

		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultConversionService());
		binderFactory = new ServletRequestDataBinderFactory(null, initializer);

		mavContainer = new ModelAndViewContainer();
		request = new MockHttpServletRequest();
		webRequest = new ServletWebRequest(request);

		Method method = getClass().getDeclaredMethod("modelAttribute",
				TestBean.class, TestBeanWithoutStringConstructor.class, Optional.class);
		testBeanModelAttr = new MethodParameter(method, 0);
		testBeanWithoutStringConstructorModelAttr = new MethodParameter(method, 1);
		testBeanWithOptionalModelAttr = new MethodParameter(method, 2);
	}

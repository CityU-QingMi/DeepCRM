	@Before
	public void setup() throws Exception {
		this.request = new ServletWebRequest(new MockHttpServletRequest());
		this.container = new ModelAndViewContainer();
		this.processor = new ModelAttributeMethodProcessor(false);

		Method method = ModelAttributeHandler.class.getDeclaredMethod("modelAttribute",
				TestBean.class, Errors.class, int.class, TestBean.class,
				TestBean.class, TestBean.class);

		this.paramNamedValidModelAttr = new SynthesizingMethodParameter(method, 0);
		this.paramErrors = new SynthesizingMethodParameter(method, 1);
		this.paramInt = new SynthesizingMethodParameter(method, 2);
		this.paramModelAttr = new SynthesizingMethodParameter(method, 3);
		this.paramBindingDisabledAttr = new SynthesizingMethodParameter(method, 4);
		this.paramNonSimpleType = new SynthesizingMethodParameter(method, 5);

		method = getClass().getDeclaredMethod("annotatedReturnValue");
		this.returnParamNamedModelAttr = new MethodParameter(method, -1);

		method = getClass().getDeclaredMethod("notAnnotatedReturnValue");
		this.returnParamNonSimpleType = new MethodParameter(method, -1);
	}

	@Before
	public void setup() throws Exception {
		this.resolver = new PathVariableMethodArgumentResolver(null, new ReactiveAdapterRegistry());

		Method method = ReflectionUtils.findMethod(getClass(), "handle", (Class<?>[]) null);
		paramNamedString = new SynthesizingMethodParameter(method, 0);
		paramString = new SynthesizingMethodParameter(method, 1);
		paramNotRequired = new SynthesizingMethodParameter(method, 2);
		paramOptional = new SynthesizingMethodParameter(method, 3);
		paramMono = new SynthesizingMethodParameter(method, 4);
	}

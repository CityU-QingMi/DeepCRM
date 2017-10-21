	@Before
	public void setup() throws Exception {
		resolver = new RequestHeaderMapMethodArgumentResolver(new ReactiveAdapterRegistry());

		Method method = ReflectionUtils.findMethod(getClass(), "params", (Class<?>[]) null);
		paramMap = new SynthesizingMethodParameter(method, 0);
		paramMultiValueMap = new SynthesizingMethodParameter(method, 1);
		paramHttpHeaders = new SynthesizingMethodParameter(method, 2);
		paramUnsupported = new SynthesizingMethodParameter(method, 3);
		paramUnsupported = new SynthesizingMethodParameter(method, 3);
		paramAlsoUnsupported = new SynthesizingMethodParameter(method, 4);
	}

	@Before
	public void setup() throws Exception {
		@SuppressWarnings("resource")
		GenericApplicationContext cxt = new GenericApplicationContext();
		cxt.refresh();
		this.resolver = new HeaderMethodArgumentResolver(new DefaultConversionService(), cxt.getBeanFactory());

		Method method = ReflectionUtils.findMethod(getClass(), "handleMessage", (Class<?>[]) null);
		this.paramRequired = new SynthesizingMethodParameter(method, 0);
		this.paramNamedDefaultValueStringHeader = new SynthesizingMethodParameter(method, 1);
		this.paramSystemPropertyDefaultValue = new SynthesizingMethodParameter(method, 2);
		this.paramSystemPropertyName = new SynthesizingMethodParameter(method, 3);
		this.paramNotAnnotated = new SynthesizingMethodParameter(method, 4);
		this.paramOptional = new SynthesizingMethodParameter(method, 5);
		this.paramNativeHeader = new SynthesizingMethodParameter(method, 6);

		this.paramRequired.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
		GenericTypeResolver.resolveParameterType(this.paramRequired, HeaderMethodArgumentResolver.class);
	}

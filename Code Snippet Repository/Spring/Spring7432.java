	@Before
	public void setup() throws Exception {
		this.resolver = new DestinationVariableMethodArgumentResolver(new DefaultConversionService());

		Method method = getClass().getDeclaredMethod("handleMessage", String.class, String.class, String.class);
		this.paramAnnotated = new MethodParameter(method, 0);
		this.paramAnnotatedValue = new MethodParameter(method, 1);
		this.paramNotAnnotated = new MethodParameter(method, 2);

		this.paramAnnotated.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
		GenericTypeResolver.resolveParameterType(this.paramAnnotated, DestinationVariableMethodArgumentResolver.class);
		this.paramAnnotatedValue.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
		GenericTypeResolver.resolveParameterType(this.paramAnnotatedValue, DestinationVariableMethodArgumentResolver.class);
	}

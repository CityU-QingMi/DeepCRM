	@Before
	public void setup() throws Exception {

		this.resolver = new PayloadArgumentResolver(new StringMessageConverter(), testValidator());

		Method payloadMethod = PayloadArgumentResolverTests.class.getDeclaredMethod(
				"handleMessage", String.class, String.class, Locale.class,
				String.class, String.class, String.class, String.class);

		this.paramAnnotated = new SynthesizingMethodParameter(payloadMethod, 0);
		this.paramAnnotatedNotRequired = new SynthesizingMethodParameter(payloadMethod, 1);
		this.paramAnnotatedRequired = new SynthesizingMethodParameter(payloadMethod, 2);
		this.paramWithSpelExpression = new SynthesizingMethodParameter(payloadMethod, 3);
		this.paramValidated = new SynthesizingMethodParameter(payloadMethod, 4);
		this.paramValidated.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		this.paramValidatedNotAnnotated = new SynthesizingMethodParameter(payloadMethod, 5);
		this.paramNotAnnotated = new SynthesizingMethodParameter(payloadMethod, 6);
	}

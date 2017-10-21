	@Before
	public void setup() throws Exception {
		this.resolver = new PathVariableMapMethodArgumentResolver(new ReactiveAdapterRegistry());

		Method method = ReflectionUtils.findMethod(getClass(), "handle", (Class<?>[]) null);
		this.paramMap = new MethodParameter(method, 0);
		this.paramNamedMap = new MethodParameter(method, 1);
		this.paramMapNoAnnot = new MethodParameter(method, 2);
		this.paramMonoMap = new MethodParameter(method, 3);
	}

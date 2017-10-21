	@Before
	public void setup() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();

		ReactiveAdapterRegistry adapterRegistry = new ReactiveAdapterRegistry();
		this.resolver = new CookieValueMethodArgumentResolver(context.getBeanFactory(), adapterRegistry);
		this.bindingContext = new BindingContext();

		Method method = ReflectionUtils.findMethod(getClass(), "params", (Class<?>[]) null);
		this.cookieParameter = new SynthesizingMethodParameter(method, 0);
		this.cookieStringParameter = new SynthesizingMethodParameter(method, 1);
		this.stringParameter = new SynthesizingMethodParameter(method, 2);
		this.cookieMonoParameter = new SynthesizingMethodParameter(method, 3);
	}

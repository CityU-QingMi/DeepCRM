	@BeforeClass
	public static void setupOnce() {
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		adapter.setApplicationContext(new StaticWebApplicationContext());
		adapter.afterPropertiesSet();

		RESOLVER_COUNT = adapter.getArgumentResolvers().size();
		INIT_BINDER_RESOLVER_COUNT = adapter.getInitBinderArgumentResolvers().size();
		HANDLER_COUNT = adapter.getReturnValueHandlers().size();
	}

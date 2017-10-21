	@Test
	public void beanCondition() {
		this.context = new AnnotationConfigApplicationContext(BeanConditionConfig.class);
		Cache cache = getCache();
		FooService service = this.context.getBean(FooService.class);

		Object key = new Object();
		service.getWithCondition(key);
		assertCacheMiss(key, cache);
	}

	@Test
	public void spr13081ConfigNoCacheNameIsRequired() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spr13081Config.class);
		MyCacheResolver cacheResolver = context.getBean(MyCacheResolver.class);
		Spr13081Service bean = context.getBean(Spr13081Service.class);

		assertNull(cacheResolver.getCache("foo").get("foo"));
		Object result = bean.getSimple("foo");  // cache name = id
		assertEquals(result, cacheResolver.getCache("foo").get("foo").get());
	}

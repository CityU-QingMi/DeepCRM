	@Test
	public void spr14853AdaptsToOptionalWithSync() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spr14853Config.class);
		Spr14853Service bean = context.getBean(Spr14853Service.class);
		Cache cache = context.getBean(CacheManager.class).getCache("itemCache");

		TestBean tb = new TestBean("tb1");
		bean.insertItem(tb);
		assertSame(tb, bean.findById("tb1").get());
		assertSame(tb, cache.get("tb1").get());

		cache.clear();
		TestBean tb2 = bean.findById("tb1").get();
		assertNotSame(tb, tb2);
		assertSame(tb2, cache.get("tb1").get());
	}

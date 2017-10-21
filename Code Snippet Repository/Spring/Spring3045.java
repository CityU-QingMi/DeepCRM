	@Test
	public void spr15271FindsOnInterfaceWithInterfaceProxy() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spr15271ConfigA.class);
		Spr15271Interface bean = context.getBean(Spr15271Interface.class);
		Cache cache = context.getBean(CacheManager.class).getCache("itemCache");

		TestBean tb = new TestBean("tb1");
		bean.insertItem(tb);
		assertSame(tb, bean.findById("tb1").get());
		assertSame(tb, cache.get("tb1").get());
	}

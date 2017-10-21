	@Test
	public void spr11592GetSimple() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spr11592Config.class);
		Spr11592Service bean = context.getBean(Spr11592Service.class);
		Cache cache = context.getBean("cache", Cache.class);

		String key = "1";
		Object result = bean.getSimple("1");
		verify(cache, times(1)).get(key);  // first call: cache miss

		Object cachedResult = bean.getSimple("1");
		assertSame(result, cachedResult);
		verify(cache, times(2)).get(key);  // second call: cache hit

		context.close();
	}

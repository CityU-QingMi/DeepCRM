	@Test
	public void spr11592GetNeverCache() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spr11592Config.class);
		Spr11592Service bean = context.getBean(Spr11592Service.class);
		Cache cache = context.getBean("cache", Cache.class);

		String key = "1";
		Object result = bean.getNeverCache("1");
		verify(cache, times(0)).get(key);  // no cache hit at all, caching disabled

		Object cachedResult = bean.getNeverCache("1");
		assertNotSame(result, cachedResult);
		verify(cache, times(0)).get(key);  // caching disabled

		context.close();
	}

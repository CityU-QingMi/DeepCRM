	@Before
	public void setup() {
		this.ctx = getApplicationContext();
		this.cs = ctx.getBean("service", CacheableService.class);
		this.ccs = ctx.getBean("classService", CacheableService.class);
		this.cm = ctx.getBean("cacheManager", CacheManager.class);

		Collection<String> cn = this.cm.getCacheNames();
		assertTrue(cn.contains("testCache"));
		assertTrue(cn.contains("secondary"));
		assertTrue(cn.contains("primary"));
	}

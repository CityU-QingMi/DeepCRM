	@Test
	@Ignore("")
	public void emptyConfigSupport() {
		load(EmptyConfigSupportConfig.class);
		AnnotationCacheAspect aspect = this.ctx.getBean(AnnotationCacheAspect.class);
		assertNotNull(aspect.getCacheResolver());
		assertEquals(SimpleCacheResolver.class, aspect.getCacheResolver().getClass());
		assertSame(this.ctx.getBean(CacheManager.class),
				((SimpleCacheResolver) aspect.getCacheResolver()).getCacheManager());
	}

	@Test
	public void severalCachesNotSupported() {
		JCacheInterceptor interceptor = createInterceptor(createOperationSource(
				cacheManager, new NamedCacheResolver(cacheManager, "default", "simpleCache"),
				defaultExceptionCacheResolver, defaultKeyGenerator));

		AnnotatedJCacheableService service = new AnnotatedJCacheableService(cacheManager.getCache("default"));
		Method m = ReflectionUtils.findMethod(AnnotatedJCacheableService.class, "cache", String.class);

		try {
			interceptor.execute(dummyInvoker, service, m, new Object[] {"myId"});
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("JSR-107 only supports a single cache"));
		}
		catch (Throwable ex) {
			fail("Unexpected: " + ex);
		}
	}

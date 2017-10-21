	@Test
	public void noCacheCouldBeResolved() {
		JCacheInterceptor interceptor = createInterceptor(createOperationSource(
				cacheManager, new NamedCacheResolver(cacheManager), // Returns empty list
				defaultExceptionCacheResolver, defaultKeyGenerator));

		AnnotatedJCacheableService service = new AnnotatedJCacheableService(cacheManager.getCache("default"));
		Method m = ReflectionUtils.findMethod(AnnotatedJCacheableService.class, "cache", String.class);

		try {
			interceptor.execute(dummyInvoker, service, m, new Object[] {"myId"});
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("Cache could not have been resolved for"));
		}
		catch (Throwable ex) {
			fail("Unexpected: " + ex);
		}
	}

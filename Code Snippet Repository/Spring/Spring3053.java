	@Test
	public void multipleComposedAnnotations() throws Exception {
		Collection<CacheOperation> ops = getOps(AnnotatedClass.class, "multipleComposed", 4);
		Iterator<CacheOperation> it = ops.iterator();

		CacheOperation cacheOperation = it.next();
		assertThat(cacheOperation, instanceOf(CacheableOperation.class));
		assertThat(cacheOperation.getCacheNames(), equalTo(Collections.singleton("directly declared")));
		assertThat(cacheOperation.getKey(), equalTo(""));

		cacheOperation = it.next();
		assertThat(cacheOperation, instanceOf(CacheableOperation.class));
		assertThat(cacheOperation.getCacheNames(), equalTo(Collections.singleton("composedCache")));
		assertThat(cacheOperation.getKey(), equalTo("composedKey"));

		cacheOperation = it.next();
		assertThat(cacheOperation, instanceOf(CacheableOperation.class));
		assertThat(cacheOperation.getCacheNames(), equalTo(Collections.singleton("foo")));
		assertThat(cacheOperation.getKey(), equalTo(""));

		cacheOperation = it.next();
		assertThat(cacheOperation, instanceOf(CacheEvictOperation.class));
		assertThat(cacheOperation.getCacheNames(), equalTo(Collections.singleton("composedCacheEvict")));
		assertThat(cacheOperation.getKey(), equalTo("composedEvictionKey"));
	}

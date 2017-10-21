	@Test
	public void singleComposedAnnotation() throws Exception {
		Collection<CacheOperation> ops = getOps(AnnotatedClass.class, "singleComposed", 2);
		Iterator<CacheOperation> it = ops.iterator();

		CacheOperation cacheOperation = it.next();
		assertThat(cacheOperation, instanceOf(CacheableOperation.class));
		assertThat(cacheOperation.getCacheNames(), equalTo(Collections.singleton("directly declared")));
		assertThat(cacheOperation.getKey(), equalTo(""));

		cacheOperation = it.next();
		assertThat(cacheOperation, instanceOf(CacheableOperation.class));
		assertThat(cacheOperation.getCacheNames(), equalTo(Collections.singleton("composedCache")));
		assertThat(cacheOperation.getKey(), equalTo("composedKey"));
	}

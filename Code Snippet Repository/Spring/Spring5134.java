	private void assertGetAllMergedAnnotationsBehavior(AnnotatedElement element) {
		assertNotNull(element);

		Set<Cacheable> cacheables = getAllMergedAnnotations(element, Cacheable.class);
		assertNotNull(cacheables);
		assertEquals(2, cacheables.size());

		Iterator<Cacheable> iterator = cacheables.iterator();
		Cacheable fooCacheable = iterator.next();
		Cacheable barCacheable = iterator.next();
		assertEquals("fooKey", fooCacheable.key());
		assertEquals("fooCache", fooCacheable.value());
		assertEquals("barKey", barCacheable.key());
		assertEquals("barCache", barCacheable.value());
	}

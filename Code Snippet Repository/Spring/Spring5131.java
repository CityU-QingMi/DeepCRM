	@Test
	public void findMultipleNoninheritedComposedAnnotationsOnClass() {
		Class<?> element = MultipleNoninheritedComposedCachesClass.class;
		Set<Cacheable> cacheables = findAllMergedAnnotations(element, Cacheable.class);
		assertNotNull(cacheables);
		assertEquals(2, cacheables.size());

		Iterator<Cacheable> iterator = cacheables.iterator();
		Cacheable cacheable1 = iterator.next();
		Cacheable cacheable2 = iterator.next();
		assertEquals("noninheritedCache1", cacheable1.value());
		assertEquals("noninheritedCache2", cacheable2.value());
	}

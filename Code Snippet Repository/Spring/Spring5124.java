	private void assertGetRepeatableAnnotations(AnnotatedElement element) {
		assertNotNull(element);

		Set<PeteRepeat> peteRepeats = getMergedRepeatableAnnotations(element, PeteRepeat.class);
		assertNotNull(peteRepeats);
		assertEquals(3, peteRepeats.size());

		Iterator<PeteRepeat> iterator = peteRepeats.iterator();
		assertEquals("A", iterator.next().value());
		assertEquals("B", iterator.next().value());
		assertEquals("C", iterator.next().value());
	}

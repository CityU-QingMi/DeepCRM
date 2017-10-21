	@Test
	public void testBuildCollectionFromMixtureOfReferencesAndValues() throws Exception {
		MixedCollectionBean jumble = (MixedCollectionBean) this.beanFactory.getBean("jumble");
		assertTrue("Expected 5 elements, not " + jumble.getJumble().size(),
				jumble.getJumble().size() == 5);
		List l = (List) jumble.getJumble();
		assertTrue(l.get(0).equals(this.beanFactory.getBean("david")));
		assertTrue(l.get(1).equals("literal"));
		assertTrue(l.get(2).equals(this.beanFactory.getBean("jenny")));
		assertTrue(l.get(3).equals("rod"));
		Object[] array = (Object[]) l.get(4);
		assertTrue(array[0].equals(this.beanFactory.getBean("david")));
		assertTrue(array[1].equals("literal2"));
	}

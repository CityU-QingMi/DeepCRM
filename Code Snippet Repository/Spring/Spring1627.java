	@Test
	public void testBuildCollectionFromMixtureOfReferencesAndValues() throws Exception {
		MixedCollectionBean jumble = (MixedCollectionBean) this.beanFactory.getBean("jumble");
		assertTrue("Expected 3 elements, not " + jumble.getJumble().size(),
				jumble.getJumble().size() == 3);
		List l = (List) jumble.getJumble();
		assertTrue(l.get(0).equals("literal"));
		Integer[] array1 = (Integer[]) l.get(1);
		assertTrue(array1[0].equals(new Integer(2)));
		assertTrue(array1[1].equals(new Integer(4)));
		int[] array2 = (int[]) l.get(2);
		assertTrue(array2[0] == 3);
		assertTrue(array2[1] == 5);
	}

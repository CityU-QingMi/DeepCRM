	@Test
	public void factoryReferences() {
		DummyFactory factory = (DummyFactory) getBeanFactory().getBean("&singletonFactory");

		DummyReferencer ref = (DummyReferencer) getBeanFactory().getBean("factoryReferencer");
		assertTrue(ref.getTestBean1() == ref.getTestBean2());
		assertTrue(ref.getDummyFactory() == factory);

		DummyReferencer ref2 = (DummyReferencer) getBeanFactory().getBean("factoryReferencerWithConstructor");
		assertTrue(ref2.getTestBean1() == ref2.getTestBean2());
		assertTrue(ref2.getDummyFactory() == factory);
	}

	@Test
	public void withScopedSupplier() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("testBean",
				new RootBeanDefinition(String.class, RootBeanDefinition.SCOPE_PROTOTYPE, ac::toString));
		ac.refresh();

		assertNotSame(ac.getBean("testBean"), ac.getBean("testBean"));
		assertEquals(ac.getBean("testBean"), ac.getBean(String.class));
		assertEquals(ac.getBean("testBean"), ac.getBean(CharSequence.class));
		assertEquals(ac.toString(), ac.getBean("testBean"));
	}

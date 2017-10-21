	@Test
	public void withSingletonSupplier() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("testBean", new RootBeanDefinition(String.class, ac::toString));
		ac.refresh();

		assertSame(ac.getBean("testBean"), ac.getBean("testBean"));
		assertSame(ac.getBean("testBean"), ac.getBean(String.class));
		assertSame(ac.getBean("testBean"), ac.getBean(CharSequence.class));
		assertEquals(ac.toString(), ac.getBean("testBean"));
	}

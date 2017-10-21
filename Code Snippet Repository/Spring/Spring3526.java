	@Test
	public void getBeanForClass() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("testBean", new RootBeanDefinition(String.class));
		ac.refresh();

		assertEquals("", ac.getBean("testBean"));
		assertSame(ac.getBean("testBean"), ac.getBean(String.class));
		assertSame(ac.getBean("testBean"), ac.getBean(CharSequence.class));

		try {
			assertSame(ac.getBean("testBean"), ac.getBean(Object.class));
			fail("Should have thrown NoUniqueBeanDefinitionException");
		}
		catch (NoUniqueBeanDefinitionException ex) {
			// expected
		}
	}

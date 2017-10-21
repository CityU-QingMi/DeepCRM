	@Test
	public void aliasing() {
		BeanFactory bf = getBeanFactory();
		if (!(bf instanceof ConfigurableBeanFactory)) {
			return;
		}
		ConfigurableBeanFactory cbf = (ConfigurableBeanFactory) bf;

		String alias = "rods alias";
		try {
			cbf.getBean(alias);
			fail("Shouldn't permit factory get on normal bean");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// Ok
			assertTrue(alias.equals(ex.getBeanName()));
		}

		// Create alias
		cbf.registerAlias("rod", alias);
		Object rod = getBeanFactory().getBean("rod");
		Object aliasRod = getBeanFactory().getBean(alias);
		assertTrue(rod == aliasRod);
	}

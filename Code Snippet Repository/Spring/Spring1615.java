	@Test
	public void getCountForFactoryClass() {
		assertTrue("Should have 2 factories, not " +
				getListableBeanFactory().getBeanNamesForType(FactoryBean.class).length,
				getListableBeanFactory().getBeanNamesForType(FactoryBean.class).length == 2);

		assertTrue("Should have 2 factories, not " +
				getListableBeanFactory().getBeanNamesForType(FactoryBean.class).length,
				getListableBeanFactory().getBeanNamesForType(FactoryBean.class).length == 2);
	}

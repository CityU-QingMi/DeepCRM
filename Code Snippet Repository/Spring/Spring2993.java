	@Test
	public void testRelatedCausesFromConstructorResolution() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(xbf).loadBeanDefinitions(CONSTRUCTOR_ARG_CONTEXT);

		try {
			xbf.getBean("rod2Accessor");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.toString().indexOf("touchy") != -1);
			ex.printStackTrace();
			assertNull(ex.getRelatedCauses());
		}
	}

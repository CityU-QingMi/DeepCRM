	@Test
	public void testPossibleMatches() {
		try {
			DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.add("ag", "foobar");
			RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
			bd.setPropertyValues(pvs);
			lbf.registerBeanDefinition("tb", bd);
			lbf.getBean("tb");
			fail("Should throw exception on invalid property");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getCause() instanceof NotWritablePropertyException);
			NotWritablePropertyException cause = (NotWritablePropertyException) ex.getCause();
			// expected
			assertEquals(1, cause.getPossibleMatches().length);
			assertEquals("age", cause.getPossibleMatches()[0]);
		}
	}

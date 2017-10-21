	@Test
	public void testPropertyInjection() throws Exception {
		try {
			beanFactory.getBean("property-injection");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMessage().contains("security"));
		}

		beanFactory.getBean("working-property-injection");
	}

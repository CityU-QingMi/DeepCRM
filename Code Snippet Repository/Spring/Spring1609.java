	@Test
	public void getSharedInstanceByNonmatchingClass() {
		try {
			getBeanFactory().getBean("rod", BeanFactory.class);
			fail("Rod bean is not of type BeanFactory; getBeanInstance(rod, BeanFactory.class) should throw BeanNotOfRequiredTypeException");
		}
		catch (BeanNotOfRequiredTypeException ex) {
			// So far, so good
			assertTrue("Exception has correct bean name", ex.getBeanName().equals("rod"));
			assertTrue("Exception requiredType must be BeanFactory.class", ex.getRequiredType().equals(BeanFactory.class));
			assertTrue("Exception actualType as TestBean.class", TestBean.class.isAssignableFrom(ex.getActualType()));
		}
	}

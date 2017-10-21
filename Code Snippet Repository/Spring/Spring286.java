	@Test
	public void testValidPatternMatching() {
		TypePatternClassFilter tpcf = new TypePatternClassFilter("org.springframework.tests.sample.beans.*");
		assertTrue("Must match: in package", tpcf.matches(TestBean.class));
		assertTrue("Must match: in package", tpcf.matches(ITestBean.class));
		assertTrue("Must match: in package", tpcf.matches(IOther.class));
		assertFalse("Must be excluded: in wrong package", tpcf.matches(DeepBean.class));
		assertFalse("Must be excluded: in wrong package", tpcf.matches(BeanFactory.class));
		assertFalse("Must be excluded: in wrong package", tpcf.matches(DefaultListableBeanFactory.class));
	}

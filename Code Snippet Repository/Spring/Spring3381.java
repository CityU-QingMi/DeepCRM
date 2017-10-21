	@Test
	public void testSameScopeOnDifferentBeans() throws Exception {
		Object beanAInScope = ctx.getBean("scopedClass");
		Object beanBInScope = ctx.getBean("scopedInterface");

		assertNotSame(beanAInScope, beanBInScope);

		customScope.createNewScope = true;

		Object newBeanAInScope = ctx.getBean("scopedClass");
		Object newBeanBInScope = ctx.getBean("scopedInterface");

		assertNotSame(newBeanAInScope, newBeanBInScope);
		assertNotSame(newBeanAInScope, beanAInScope);
		assertNotSame(newBeanBInScope, beanBInScope);
	}

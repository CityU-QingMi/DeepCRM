	private void genericTestScope(String beanName) throws Exception {
		String message = "scope is ignored";
		Object bean1 = ctx.getBean(beanName);
		Object bean2 = ctx.getBean(beanName);

		assertSame(message, bean1, bean2);

		Object bean3 = ctx.getBean(beanName);

		assertSame(message, bean1, bean3);

		// make the scope create a new object
		customScope.createNewScope = true;

		Object newBean1 = ctx.getBean(beanName);
		assertNotSame(message, bean1, newBean1);

		Object sameBean1 = ctx.getBean(beanName);

		assertSame(message, newBean1, sameBean1);

		// make the scope create a new object
		customScope.createNewScope = true;

		Object newBean2 = ctx.getBean(beanName);
		assertNotSame(message, newBean1, newBean2);

		// make the scope create a new object .. again
		customScope.createNewScope = true;

		Object newBean3 = ctx.getBean(beanName);
		assertNotSame(message, newBean2, newBean3);
	}

	@Test
	public void testContainerPrivileges() throws Exception {
		AccessControlContext acc = provider.getAccessControlContext();

		AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {

			@Override
			public Object run() throws Exception {
				beanFactory.getBean("working-factory-method");
				beanFactory.getBean("container-execution");
				return null;
			}
		}, acc);
	}

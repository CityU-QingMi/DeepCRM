	@Test
	public void properExceptionForResolvedProxyDependencyMismatch() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AsyncConfig.class, AsyncBeanUser.class, AsyncBeanWithInterface.class);

		try {
			ctx.refresh();
			fail("Should have thrown UnsatisfiedDependencyException");
		}
		catch (UnsatisfiedDependencyException ex) {
			ex.printStackTrace();
			assertTrue(ex.getCause() instanceof BeanNotOfRequiredTypeException);
		}
	}

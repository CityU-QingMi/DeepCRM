	@Test
	public void properExceptionForExistingProxyDependencyMismatch() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AsyncConfig.class, AsyncBeanWithInterface.class, AsyncBeanUser.class);

		try {
			ctx.refresh();
			fail("Should have thrown UnsatisfiedDependencyException");
		}
		catch (UnsatisfiedDependencyException ex) {
			ex.printStackTrace();
			assertTrue(ex.getCause() instanceof BeanNotOfRequiredTypeException);
		}
	}

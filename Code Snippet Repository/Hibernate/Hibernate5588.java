	@Test
	public void testJpaBootstrapWithoutCdiAvailablePassingCdi() throws Throwable {
		Class delegateClass = Thread.currentThread().getContextClassLoader().loadClass(
				"org.hibernate.jpa.test.cdi.NoCdiAvailableTestDelegate"
		);
		Method mainMethod = delegateClass.getMethod( "passingBeanManager" );
		try {
			mainMethod.invoke( null );
			fail( "Expecting failure from missing CDI classes" );
		}
		catch (InvocationTargetException expected) {
			// hard to assert specific exception types due to classloader trickery
		}
	}

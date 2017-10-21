	@Test
	public void testJpaBootstrapWithoutCdiAvailable() throws Exception {
		Class delegateClass = Thread.currentThread().getContextClassLoader().loadClass(
				"org.hibernate.jpa.test.cdi.NoCdiAvailableTestDelegate"
		);
		Method mainMethod = delegateClass.getMethod( "passingNoBeanManager" );
		EntityManagerFactory entityManagerFactory = null;
		try {
			entityManagerFactory = (EntityManagerFactory) mainMethod.invoke( null );
		}
		finally {
			if (entityManagerFactory != null ) {
				entityManagerFactory.close();
			}
		}
	}

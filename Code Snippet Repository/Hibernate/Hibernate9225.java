	private void checkObject(Object entityOrProxy, Set expectedInitializedObjects, Set expectedReadOnlyObjects, Session s) {
		boolean isExpectedToBeInitialized = expectedInitializedObjects.contains( entityOrProxy );
		boolean isExpectedToBeReadOnly = expectedReadOnlyObjects.contains( entityOrProxy );
		SessionImplementor si = (SessionImplementor) s;
		assertEquals( isExpectedToBeInitialized, Hibernate.isInitialized( entityOrProxy ) );
		assertEquals( isExpectedToBeReadOnly, s.isReadOnly( entityOrProxy ) );
		if ( Hibernate.isInitialized( entityOrProxy ) ) {
			Object entity = ( entityOrProxy instanceof HibernateProxy ?
					( ( HibernateProxy ) entityOrProxy ).getHibernateLazyInitializer().getImplementation( si ) :
					entityOrProxy
			);
			assertNotNull( entity );
			assertEquals( isExpectedToBeReadOnly, s.isReadOnly( entity ));
		}
	}

	@Test
	public void testInvalidAttributeCausesIllegalArgumentException() {
		// should not matter the exact subclass of ManagedType since this is implemented on the base class but
		// check each anyway..

		// entity
		checkNonExistentAttributeAccess( entityManagerFactory().getMetamodel().entity( Fridge.class ) );

		// embeddable
		checkNonExistentAttributeAccess( entityManagerFactory().getMetamodel().embeddable( Address.class ) );
	}

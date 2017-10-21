	private void checkNonExistentAttributeAccess(ManagedType managedType) {
		final String NAME = "NO_SUCH_ATTRIBUTE";
		try {
			managedType.getAttribute( NAME );
			fail( "Lookup of non-existent attribute (getAttribute) should have caused IAE : " + managedType );
		}
		catch (IllegalArgumentException expected) {
		}
		try {
			managedType.getSingularAttribute( NAME );
			fail( "Lookup of non-existent attribute (getSingularAttribute) should have caused IAE : " + managedType );
		}
		catch (IllegalArgumentException expected) {
		}
		try {
			managedType.getCollection( NAME );
			fail( "Lookup of non-existent attribute (getCollection) should have caused IAE : " + managedType );
		}
		catch (IllegalArgumentException expected) {
		}
	}

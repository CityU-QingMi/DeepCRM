	private void checkReturnedTypes(QueryTranslator oldQueryTranslator, QueryTranslator newQueryTranslator) {
		// Check the returned types for a regression.
		Type[] oldReturnTypes = oldQueryTranslator.getReturnTypes();
		Type[] returnTypes = newQueryTranslator.getReturnTypes();
		assertEquals( "Return types array is not the right length!", oldReturnTypes.length, returnTypes.length );
		for ( int i = 0; i < returnTypes.length; i++ ) {
			assertNotNull( returnTypes[i] );
			assertNotNull( oldReturnTypes[i] );
			assertEquals( "Returned types did not match!", oldReturnTypes[i].getReturnedClass(), returnTypes[i].getReturnedClass() );
			System.out.println("returnedType[" + i + "] = " + returnTypes[i] + " oldReturnTypes[" + i + "] = " + oldReturnTypes[i]);
		}
	}

	private void check(boolean expectedEquals, NativeSQLQueryReturn queryReturn1, NativeSQLQueryReturn queryReturn2) {
		if ( expectedEquals ) {
			assertTrue( queryReturn1.equals( queryReturn2 ) );
			assertTrue( queryReturn2.equals( queryReturn1 ) );
			assertTrue( queryReturn1.hashCode() == queryReturn2.hashCode() );
		}
		else {
			assertFalse( queryReturn1.equals( queryReturn2 ) );
			assertFalse( queryReturn2.equals( queryReturn1 ) );
			assertFalse( queryReturn1.hashCode() == queryReturn2.hashCode() );
		}
	}

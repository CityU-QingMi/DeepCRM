	private void checkColumnSize(String s) {
		if ( s.toLowerCase().contains( "manager_age" ) ) {
			if ( !s.contains( "15" ) ) {
				fail( expectedMessage( "manager_age", 15, s ) );
			}
		}
		else if ( s.toLowerCase().contains( "manager_birthday" ) ) {
			if ( !s.contains( "255" ) ) {
				fail( expectedMessage( "manager_birthday", 255, s ) );
			}
		}
		else if ( s.toLowerCase().contains( "manager_name" ) ) {
			if ( !s.contains( "20" ) ) {
				fail( expectedMessage( "manager_name", 20, s ) );
			}
		}
		else if ( s.toLowerCase().contains( "age" ) ) {
			if ( !s.contains( "15" ) ) {
				fail( expectedMessage( "age", 15, s ) );
			}
		}
		else if ( s.toLowerCase().contains( "birthday" ) ) {
			if ( !s.contains( "255" ) ) {
				fail( expectedMessage( "birthday", 255, s ) );
			}
		}
		else if ( s.toLowerCase().contains( "name" ) ) {
			if ( !s.contains( "20" ) ) {
				fail( expectedMessage( "name", 20, s ) );
			}
		}
	}

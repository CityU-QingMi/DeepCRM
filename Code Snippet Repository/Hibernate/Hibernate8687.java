	private void compare(JoinWalker walker, LoadQueryDetails details) {
		System.out.println( "------ SQL -----------------------------------------------------------------" );
		System.out.println( "WALKER    : " + walker.getSQLString() );
		System.out.println( "LOAD-PLAN : " + details.getSqlStatement() );
		System.out.println( "----------------------------------------------------------------------------" );
		System.out.println( );
		System.out.println( "------ SUFFIXES ------------------------------------------------------------" );
		System.out.println( "WALKER    : " + StringHelper.join( ", ",  walker.getSuffixes() ) + " : "
									+ StringHelper.join( ", ", walker.getCollectionSuffixes() ) );
		System.out.println( "----------------------------------------------------------------------------" );
		System.out.println( );
	}

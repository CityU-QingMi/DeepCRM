	public void test(int numberOfRuns) throws IOException {
		List<Long> unauditedRuns = new ArrayList<Long>();
		List<Long> auditedRuns = new ArrayList<Long>();

		init( true, null );
		long audited = run( numberOfRuns, auditedRuns );
		close();

		init( false, null );
		long unaudited = run( numberOfRuns, unauditedRuns );
		close();

		for ( int i = 0; i <= numberOfRuns; i++ ) {
			System.out.println( "RUN " + i );
			printResults( unauditedRuns.get( i ), auditedRuns.get( i ) );
			System.out.println();
		}

		System.out.println( "TOTAL" );
		printResults( unaudited, audited );
	}

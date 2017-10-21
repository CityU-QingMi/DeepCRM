	public long run(int numberOfRuns, List<Long> results) {
		long total = 0;
		for ( int i = 0; i <= numberOfRuns; i++ ) {
			System.out.println();
			System.out.println( "RUN " + i );
			reset();
			doTest();
			results.add( runTotal );
			total += runTotal;

			newEntityManager();

/**/
/**/
/**/
/**/
/**/
		}

		return total;
	}

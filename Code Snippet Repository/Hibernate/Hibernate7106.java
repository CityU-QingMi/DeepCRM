	@Test
	public void testBatchInsertUpdate() {
		long start = System.currentTimeMillis();
		final int N = 5000; //26 secs with batch flush, 26 without
		//final int N = 100000; //53 secs with batch flush, OOME without
		//final int N = 250000; //137 secs with batch flush, OOME without
		int batchSize = sessionFactory().getSettings().getJdbcBatchSize();
		doBatchInsertUpdate( N, batchSize );
		System.out.println( System.currentTimeMillis() - start );
	}

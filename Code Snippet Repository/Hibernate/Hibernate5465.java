	@Test
	public void testHiLoAlgorithm() {
		sessionImpl = (SessionImpl) sessionFactory.openSession();
		try {
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			// historically the hilo generators skipped the initial block of values;
			// so the first generated id value is maxlo + 1, here be 4
			assertEquals(4L, generateValue());
			// which should also perform the first read on the sequence which should set it to its "start with" value (1)
			assertEquals(1L, extractSequenceValue());

			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			assertEquals(5L, generateValue());
			assertEquals(1L, extractSequenceValue());

			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			assertEquals(6L, generateValue());
			assertEquals(1L, extractSequenceValue());

			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			assertEquals(7L, generateValue());
			// unlike the newer strategies, the db value will not get update here. It gets updated on the next invocation
			// after a clock over
			assertEquals(1L, extractSequenceValue());

			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			assertEquals(8L, generateValue());
			// this should force an increment in the sequence value
			assertEquals(2L, extractSequenceValue());
		} finally {
			sessionImpl.close();
		}
	}

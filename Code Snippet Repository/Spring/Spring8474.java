	@Test
	public void transactionalTest() {
		assertEquals(1, txManager1.begun);
		assertEquals(1, txManager1.inflight);
		assertEquals(0, txManager1.commits);
		assertEquals(0, txManager1.rollbacks);

		assertEquals(0, txManager2.begun);
		assertEquals(0, txManager2.inflight);
		assertEquals(0, txManager2.commits);
		assertEquals(0, txManager2.rollbacks);
	}

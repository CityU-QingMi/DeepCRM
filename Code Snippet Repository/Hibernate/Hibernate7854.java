	@Test(expected = TransactionRequiredException.class)
	public void testFlushDisallowingOutOfTransactionUpdateOperations() throws Exception {
		allowUpdateOperationOutsideTransaction = "false";
		rebuildSessionFactory();
		prepareTest();
		try (Session s = openSession()) {
			final MyEntity entity = (MyEntity) s.createQuery( "from MyEntity e where e.name = :n" )
					.setParameter( "n", "entity" )
					.uniqueResult();
			assertThat( entity, not( nullValue() ) );
			entity.setName( "changed" );
			session.flush();
		}
	}

	@Test
	public void testStoredProcedureOutParameter() {
		doInHibernate( this::sessionFactory, session -> {
			List<Object[]> persons = session
					.createNamedQuery(
							"getPerson")
					.setParameter(1, 1L)
					.getResultList();
			assertEquals(1, persons.size());
		} );
	}

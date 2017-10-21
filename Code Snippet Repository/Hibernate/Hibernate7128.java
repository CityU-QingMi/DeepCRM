	@Test
	public void testDeleteFromPerson() {
		doInHibernate( this::sessionFactory, session -> {
			//tag::batch-bulk-hql-temp-table-delete-query-example[]
			int updateCount = session.createQuery(
				"delete from Person where employed = :employed" )
			.setParameter( "employed", false )
			.executeUpdate();
			//end::batch-bulk-hql-temp-table-delete-query-example[]
			assertEquals( entityCount(), updateCount );
		});
	}

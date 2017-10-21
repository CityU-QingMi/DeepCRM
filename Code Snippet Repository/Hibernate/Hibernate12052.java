	protected void cleanupTestData() throws Exception {
		if(isCleanupTestDataUsingBulkDelete()) {
			doInHibernate( this::sessionFactory, s -> {
				s.createQuery( "delete from java.lang.Object" ).executeUpdate();
			} );
		}
		else {
			// Because of https://hibernate.atlassian.net/browse/HHH-5529,
			// we can'trely on a Bulk Delete query which will not clear the link tables in @ElementCollection or unidirectional collections
			doInHibernate( this::sessionFactory, s -> {
				s.createQuery( "from java.lang.Object" ).list().forEach( s::remove );
			} );
		}
	}

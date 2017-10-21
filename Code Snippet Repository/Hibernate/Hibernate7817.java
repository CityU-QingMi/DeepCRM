	@Test
	public void testRestrictionsOnComponentTypes() {
		doInHibernate( this::sessionFactory, session -> {
			session.enableFilter( "statusFilter" ).setParameter( "status", "active" );

			final Criteria query = session.createCriteria( Student.class );
			query.add( Restrictions.eq( "id", STUDENT_ID ) );
			query.add( Restrictions.eq( "address", new Address( "London", "Lollard St" ) ) );
			query.add( Restrictions.eq( "name", "dre" ) );

			final List list = query.list();

			assertThat( list.size(), is( 1 ) );
		});
	}

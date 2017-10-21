	@Test
	public void testSubqueryWithRestrictionsOnComponentTypes() {
		doInHibernate( this::sessionFactory, session -> {
			session.enableFilter( "statusFilter" ).setParameter( "status", "active" );

			final Criteria query = session.createCriteria( Student.class );
			query.add( Restrictions.eq( "id", STUDENT_ID ) );

			final DetachedCriteria subSelect = DetachedCriteria.forClass( Student.class );
			subSelect.setProjection( Projections.max( "age" ) );
			subSelect.add( Restrictions.eq( "id", STUDENT_ID ) );

			query.add( Property.forName( "age" ).eq( subSelect ) );
			final List list = query.list();

			assertThat( list.size(), is( 1 ) );
		});
	}

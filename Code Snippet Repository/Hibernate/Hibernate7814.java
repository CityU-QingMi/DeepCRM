	@Test
	public void testSubquery() {
		doInHibernate( this::sessionFactory, session -> {
			final Criteria query = session.createCriteria( Student.class );
			query.add( Restrictions.eq( "name", "dre" ) );

			final DetachedCriteria inner = DetachedCriteria.forClass( Student.class );
			inner.setProjection( Projections.min( "age" ) );

			query.add( Property.forName( "age" ).eq( inner ) );
			query.add( Restrictions.eq( "name", "dre" ) );
			final List list = query.list();

			assertThat( list.size(), is( 1 ) );
	   });
		doInHibernate( this::sessionFactory, session -> {
			session.enableFilter( "statusFilter" ).setParameter( "status", "deleted" );

			final Criteria query = session.createCriteria( Student.class );
			query.add( Restrictions.eq( "name", "dre" ) );

			final DetachedCriteria inner = DetachedCriteria.forClass( Student.class );
			inner.setProjection( Projections.min( "age" ) );

			query.add( Property.forName( "age" ).eq( inner ) );
			query.add( Restrictions.eq( "name", "dre" ) );
			final List list = query.list();

			assertThat( list.size(), is( 0 ) );
	   });
	}

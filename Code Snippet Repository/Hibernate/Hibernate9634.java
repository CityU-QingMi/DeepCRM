    @Test
    public void test() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			Property property = new Property( 1, 1, 1 );
			session.persist( property );
			session.persist( new PropertyHolder( 1, property ) );
			session.persist( new PropertyHolder( 2, property ) );
		} );

        assertThat(sessionFactory().getStatistics().getEntityInsertCount(), is(3L));
		sessionFactory().getStatistics().clear();

		doInHibernate( this::sessionFactory, session -> {
			session.byId( PropertyHolder.class ).load( 1 );
			session.byId( PropertyHolder.class ).load( 2 );
		} );

		assertThat( sessionFactory().getStatistics().getEntityLoadCount(), is(3L) );
		assertThat( sessionFactory().getStatistics().getPrepareStatementCount(), is(3L) );
    }

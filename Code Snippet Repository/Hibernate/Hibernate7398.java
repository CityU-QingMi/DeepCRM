	@Test
	public void testGetEntityWithNullManyToOne() {

		sessionFactory().getCache().evictAllRegions();
		sessionFactory().getStatistics().clear();

		int id = doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = new AnEntity();
					session.persist( anEntity );
					return anEntity.id;
				}
		);

		assertEquals( 1, getEntity2LCStatistics( AnEntity.class ).getPutCount() );
		assertEquals( 0, getEntity2LCStatistics( OtherEntity.class ).getPutCount() );

		sessionFactory().getStatistics().clear();

		doInHibernate(
				this::sessionFactory,
				session -> {
					final AnEntity anEntity = session.find( AnEntity.class, id );
					assertNotNull( anEntity );
					assertNull( anEntity.otherEntity );
				}
		);

		assertEquals( 0, getEntity2LCStatistics( AnEntity.class ).getPutCount() );
		assertEquals( 0, getEntity2LCStatistics( OtherEntity.class ).getPutCount() );

		assertEquals( 1, getEntity2LCStatistics( AnEntity.class ).getHitCount() );
		assertEquals( 0, getEntity2LCStatistics( OtherEntity.class ).getHitCount() );

		assertEquals( 0, getEntity2LCStatistics( AnEntity.class ).getMissCount() );
		assertEquals( 0, getEntity2LCStatistics( OtherEntity.class ).getMissCount() );
	}

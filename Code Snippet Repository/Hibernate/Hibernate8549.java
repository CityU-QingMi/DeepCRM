	@Test
	public void testCache() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Immutable im = new Immutable();
		s.save(im);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.load( im, im.getId() );
		s.getTransaction().commit();
		s.close();

		final Session s2 = openSession();
		s2.beginTransaction();
		s2.load( im, im.getId() );
		assertEquals(
				"cached object identity",
				im,
				s2.createQuery( "from Immutable im where im = ?" ).setParameter(
						0, im, s2.getTypeHelper().entity( Immutable.class )
				).uniqueResult()
		);
		s2.doWork(
				new AbstractWork() {
					@Override
					public void execute(Connection connection) throws SQLException {
						Statement st = connection.createStatement();
						st.executeUpdate( "delete from immut" );
					}
				}
		);
		s2.getTransaction().commit();
		s2.close();
	}

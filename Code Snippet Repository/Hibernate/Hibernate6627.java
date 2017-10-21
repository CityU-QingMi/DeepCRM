	@Test
	public void testHibernateSequenceNextVal() {
		Session session = openSession();
		Transaction txn = session.beginTransaction();
		HibernateSequenceEntity entity = new HibernateSequenceEntity();
		entity.setText( "sample text" );
		session.save( entity );
		txn.commit();
		session.close();
		Assert.assertNotNull( entity.getId() );
	}

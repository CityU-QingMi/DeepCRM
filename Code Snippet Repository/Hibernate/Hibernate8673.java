	@Test
	public void testTS() throws Exception {
		Session session = openSession();
		Transaction txn = session.beginTransaction();
		Simple sim = new Simple( Long.valueOf(1) );
		sim.setDate( new Date() );
		session.save( sim );
		Query q = session.createSQLQuery( "select {sim.*} from SimpleEntity {sim} where {sim}.date_ = ?" ).addEntity( "sim", Simple.class );
		q.setTimestamp( 0, sim.getDate() );
		assertTrue ( q.list().size()==1 );
		session.delete(sim);
		txn.commit();
		session.close();
	}

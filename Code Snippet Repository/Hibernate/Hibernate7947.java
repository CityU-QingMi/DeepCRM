	@Test
	public void testIndexParams() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.createQuery( "from Zoo zoo where zoo.mammals[:name] = :id" )
			.setParameter( "name", "Walrus" )
			.setParameter( "id", Long.valueOf( 123 ) )
			.list();
		s.createQuery("from Zoo zoo where zoo.mammals[:name].bodyWeight > :w")
			.setParameter("name", "Walrus")
			.setParameter("w", new Float(123.32))
			.list();
		s.createQuery("from Zoo zoo where zoo.animals[:sn].mother.bodyWeight < :mw")
			.setParameter("sn", "ant-123")
			.setParameter("mw", new Float(23.32))
			.list();
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		t.commit();
		s.close();
	}

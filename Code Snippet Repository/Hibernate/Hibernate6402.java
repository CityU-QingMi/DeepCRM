	@Test
	public void testMapKeyType() throws Exception {
		Matrix m = new Matrix();
		m.getMvalues().put( 1, 1.1f );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( m );
		s.flush();
		s.clear();
		m = (Matrix) s.get( Matrix.class, m.getId() );
		assertEquals( 1.1f, m.getMvalues().get( 1 ), 0.01f );
		tx.rollback();
		s.close();
	}

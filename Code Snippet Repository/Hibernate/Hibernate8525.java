	@Test
	public void testSaveFlush() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Fee fee = new Fee();
		s.save( fee );
		fee.setFi( "blah" );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		fee = (Fee) s.load( Fee.class, fee.getKey() );
		assertTrue( "blah".equals( fee.getFi() ) );
		s.delete(fee);
		s.getTransaction().commit();
		s.close();
	}

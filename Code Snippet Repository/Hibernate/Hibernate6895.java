	@Test
	public void testBidirectionalFkOneToOne() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Trousers trousers = new Trousers();
		TrousersZip zip = new TrousersZip();
		trousers.id = 1;
		zip.id = 2;
		trousers.zip = zip;
		zip.trousers = trousers;
		s.persist( trousers );
		s.persist( zip );
		s.getTransaction().commit();

		s.clear();

		Transaction tx = s.beginTransaction();
		trousers = ( Trousers ) s.get( Trousers.class, trousers.id );
		assertNotNull( trousers.zip );
		assertEquals( zip.id, trousers.zip.id );

		s.clear();

		zip = ( TrousersZip ) s.get( TrousersZip.class, zip.id );
		assertNotNull( zip.trousers );
		assertEquals( trousers.id, zip.trousers.id );

		s.delete( zip );
		s.delete( zip.trousers );
		tx.commit();
		s.close();
	}

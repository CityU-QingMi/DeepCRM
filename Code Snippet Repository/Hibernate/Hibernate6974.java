	@Test
	public void testConstraintsOnSuperclassProperties() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Product product1 = new Product();
		product1.setId( 1l );
		product1.setManufacturerId( 1l );
		product1.setManufacturerPartNumber( "AAFR");
		s.persist( product1 );
		s.flush();
		Product product2 = new Product();
		product2.setId( 2l );
		product2.setManufacturerId( 1l );
		product2.setManufacturerPartNumber( "AAFR");
		s.persist( product2 );
		try {
			s.flush();
			fail( "Database Exception not handled" );
		}
		catch (PersistenceException e) {
			assertTyping( JDBCException.class, e.getCause() );
			//success
		}
		finally {
			tx.rollback();
			s.close();
		}
	}

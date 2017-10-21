	@Test
	public void storeNonUniqueRelationship() throws Throwable {
		Session session = null;
		try {
			session = openSession();
			Transaction tx = session.beginTransaction();

			Item someItem = new Item( "Some Item" );
			session.save( someItem );

			Shipment shipment1 = new Shipment( someItem );
			session.save( shipment1 );

			Shipment shipment2 = new Shipment( someItem );
			session.save( shipment2 );

			tx.commit();

			fail();
		}catch (PersistenceException e){
			assertTyping( ConstraintViolationException.class, e.getCause());
			// expected
		}
		finally {
			if ( session != null ) {
				session.getTransaction().rollback();
				session.close();
			}

			cleanUpData();
		}
	}

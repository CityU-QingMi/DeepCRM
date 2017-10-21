	@Test
	public void testMultiPathMergeNonCascadedTransientEntityInCollection() throws Exception {
		// persist a simple A in the database

		Session s = openSession();
		s.beginTransaction();
		A a = new A();
		a.setData( "Anna" );
		s.save( a );
		s.getTransaction().commit();
		s.close();

		// modify detached entity
		modifyEntity( a );

		s = openSession();
		s.beginTransaction();
		a = (A) s.merge( a );
		s.getTransaction().commit();
		s.close();

		verifyModifications( a.getId() );

		// add a new (transient) G to collection in h
		// there is no cascade from H to the collection, so this should fail when merged
		assertEquals( 1, a.getHs().size() );
		H h = (H) a.getHs().iterator().next();
		G gNew = new G();
		gNew.setData( "Gail" );
		gNew.getHs().add( h );
		h.getGs().add( gNew );

		s = openSession();
		s.beginTransaction();
		try {
			s.merge( a );
			s.merge( h );
			s.getTransaction().commit();
			fail( "should have thrown IllegalStateException" );
		}
		catch (IllegalStateException expected) {
			// expected
		}
		finally {
			s.getTransaction().rollback();
		}
		s.close();
	}

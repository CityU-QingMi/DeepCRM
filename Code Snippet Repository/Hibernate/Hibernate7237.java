	@Test
	public void testMultiPathMergeNonCascadedTransientEntityInManyToOne() throws Exception {
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

		// change the many-to-one association from h to be a new (transient) A
		// there is no cascade from H to A, so this should fail when merged
		assertEquals( 1, a.getHs().size() );
		H h = (H) a.getHs().iterator().next();
		a.getHs().remove( h );
		A aNew = new A();
		aNew.setData( "Alice" );
		aNew.addH( h );

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

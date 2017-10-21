	@Test
	public void testMultiPathMergeNonCascadedTransientEntityInOneToOne() throws Exception {
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

		// change the one-to-one association from g to be a new (transient) A
		// there is no cascade from G to A, so this should fail when merged
		G g = a.getG();
		a.setG( null );
		A aNew = new A();
		aNew.setData( "Alice" );
		g.setA( aNew );
		aNew.setG( g );

		s = openSession();
		s.beginTransaction();
		try {
			s.merge( a );
			s.merge( g );
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

	@Test
	public void testNationalization() {
		Session s = openSession();
		s.getTransaction().begin();
		try {
			MyEntity e = new MyEntity( 1L );
			e.setState( "UK" );
			s.save( e );
			s.getTransaction().commit();
		}
		catch (Exception e) {
			if ( s.getTransaction().getStatus() != TransactionStatus.FAILED_COMMIT ) {
				s.getTransaction().rollback();
			}
			fail( e.getMessage() );
		}
		finally {
			s.close();
		}

		s = openSession();
		try {
			MyEntity myEntity = s.get( MyEntity.class, 1L );
			assertNotNull( myEntity );
			assertThat( myEntity.getState(), is( "UK" ) );
		}
		finally {
			s.close();
		}

	}

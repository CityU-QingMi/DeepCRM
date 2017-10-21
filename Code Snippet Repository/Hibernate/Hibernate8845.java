	@Test
	public void testSaveEntityWithNationalizedProperty() {
		Session s = openSession();
		s.getTransaction().begin();
		try {
			NationalizedEntity ne = new NationalizedEntity();
			ne.name = "Hello";
			s.save( ne );
			s.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if ( s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			s.close();
		}

		s = openSession();
		try {
			final Query query = s.createQuery( "from NationalizedEntity where name = :name" );
			query.setString( "name", "Hello" );
			final List list = query.list();
			assertThat( list.size(), is( 1 ) );
		}
		finally {
			s.close();
		}
	}

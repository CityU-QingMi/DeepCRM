	@Test
	public void testComponentNullnessChecks() {
		Session s = openSession();
		s.beginTransaction();
		Human h = new Human();
		h.setName( new Name( "Johnny", 'B', "Goode" ) );
		s.save( h );
		h = new Human();
		h.setName( new Name( "Steve", null, "Ebersole" ) );
		s.save( h );
		h = new Human();
		h.setName( new Name( "Bono", null, null ) );
		s.save( h );
		h = new Human();
		h.setName( new Name( null, null, null ) );
		s.save( h );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List results = s.createQuery( "from Human where name is null" ).list();
		assertEquals( 1, results.size() );
		results = s.createQuery( "from Human where name is not null" ).list();
		assertEquals( 3, results.size() );
		String query =
				( getDialect() instanceof DB2Dialect || getDialect() instanceof HSQLDialect ) ?
						"from Human where cast(? as string) is null" :
						"from Human where ? is null"
				;
		if ( getDialect() instanceof DerbyDialect ) {
			s.createQuery( query ).setParameter( 0, "null" ).list();
		}
		else {
			s.createQuery( query ).setParameter( 0, null ).list();
		}

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Human" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}

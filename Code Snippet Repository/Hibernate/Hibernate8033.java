	@Test
	public void testStringCasting() {
		Session s = openSession();
		s.beginTransaction();

		if ( getDialect() instanceof DerbyDialect ) {
			// the conversion from DOUBLE to VARCHAR is not supported by Derby,
			// using the short name
			s.createQuery( "select cast(char(e.theLostNumber) as string) from MyEntity e" ).list();
			// using the java class name
			s.createQuery( "select cast(char(e.theLostNumber) as java.lang.String) from MyEntity e" ).list();
			// using the fqn Hibernate Type name
			s.createQuery( "select cast(char(e.theLostNumber) as org.hibernate.type.StringType) from MyEntity e" )
					.list();
		}
		else {
			// using the short name
			s.createQuery( "select cast(e.theLostNumber as string) from MyEntity e" ).list();
			// using the java class name
			s.createQuery( "select cast(e.theLostNumber as java.lang.String) from MyEntity e" ).list();
			// using the fqn Hibernate Type name
			s.createQuery( "select cast(e.theLostNumber as org.hibernate.type.StringType) from MyEntity e" )
					.list();
		}

		s.getTransaction().commit();
		s.close();
	}

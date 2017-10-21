	@Test
	public void testOneToOneExplicitJoinColumn() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "MedicalHistory", "FK1", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "MedicalHistory", "FK2", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "MedicalHistory", "firstname", metadata() ) );

		Session s = openSession();
		s.getTransaction().begin();
		Person e = new Person( FIRST_NAME, LAST_NAME );
		s.persist( e );
		MedicalHistory d = new MedicalHistory( e );
		s.persist( d );
		s.flush();
		s.refresh( d );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		PersonId pId = new PersonId( FIRST_NAME, LAST_NAME );
		MedicalHistory d2 = (MedicalHistory) s.get( MedicalHistory.class, pId );
		Person p2 = (Person) s.get( Person.class, pId );
		assertEquals( pId.firstName, d2.patient.firstName );
		assertEquals( pId.firstName, p2.firstName );
		s.delete( d2 );
		s.delete( p2 );
		s.getTransaction().commit();
		s.close();
	}

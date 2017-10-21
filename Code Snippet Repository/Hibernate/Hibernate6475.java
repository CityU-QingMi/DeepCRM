	@Test
	public void testOneToOneExplicitJoinColumn() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "MedicalHistory", "FK1", metadata() ) );
		assertTrue( SchemaUtil.isColumnPresent( "MedicalHistory", "FK2", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "MedicalHistory", "firstname", metadata() ) );
		Person e = new Person();
		e.id = new PersonId();
		e.id.firstName = "Emmanuel";
		e.id.lastName = "Bernard";
		Session s = openSession(  );
		s.getTransaction().begin();
		s.persist( e );
		MedicalHistory d = new MedicalHistory();
		d.patient = e;
		s.persist( d );
		s.flush();
		s.clear();
		PersonId pId = new PersonId();
		pId.firstName = e.id.firstName;
		pId.lastName = e.id.lastName;
		d = (MedicalHistory) s.get( MedicalHistory.class, pId );
		assertEquals( pId.firstName, d.patient.id.firstName );
		s.delete( d );
		s.delete( d.patient );
		s.getTransaction().commit();
		s.close();
	}

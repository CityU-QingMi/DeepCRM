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
//		d.id = new PersonId();
//		d.id.firstName = "Emmanuel"; //FIXME not needed when foreign is enabled
//		d.id.lastName = "Bernard"; //FIXME not needed when foreign is enabled
		d.patient = e;
		s.persist( d );
		s.flush();
		s.clear();
		d = (MedicalHistory) s.get( MedicalHistory.class, d.id );
		assertEquals( d.id.firstName, d.patient.id.firstName );
		s.getTransaction().rollback();
		s.close();
	}

	@Test
	public void testExplicitlyAssignedDependentIdAttributeValue() {
		// even though the id is by definition generated (using the "foreign" strategy), JPA
		// still does allow manually setting the generated id attribute value which providers
		// are expected to promptly disregard :?
		Session s = openSession();
		s.beginTransaction();
		Person person = new Person( "123456789" );
		MedicalHistory medicalHistory = new MedicalHistory( "987654321", person );
		s.persist( person );
		s.persist( medicalHistory );
		s.getTransaction().commit();
		s.close();

		// again, even though we specified an id value of "987654321" prior to persist,
		// Hibernate should have replaced that with the "123456789" from the associated
		// person
		assertEquals( person.ssn, medicalHistory.patient.ssn );
		assertEquals( person, medicalHistory.patient );
		assertEquals( person.ssn, medicalHistory.id );

		s = openSession();
		s.beginTransaction();
		// Should return null...
		MedicalHistory separateMedicalHistory = (MedicalHistory) s.get( MedicalHistory.class, "987654321" );
		assertNull( separateMedicalHistory );
		// Now we should find it...
		separateMedicalHistory = (MedicalHistory) s.get( MedicalHistory.class, "123456789" );
		assertNotNull( separateMedicalHistory );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( medicalHistory );
		s.delete( person );
		s.getTransaction().commit();
		s.close();
	}

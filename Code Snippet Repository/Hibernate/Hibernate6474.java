	@Test
	public void testForeignGenerator() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "MedicalHistory", "patient_id", metadata() ) );

		Person e = new Person();
		Session s = openSession(  );
		s.getTransaction().begin();
		s.persist( e );
		MedicalHistory d = new MedicalHistory();
		d.patient = e;
		s.persist( d );
		s.flush();
		s.clear();
		d = (MedicalHistory) s.get( MedicalHistory.class, e.id);
		assertEquals( e.id, d.id );
		s.delete( d );
		s.delete( d.patient );
		s.getTransaction().rollback();
		s.close();
	}

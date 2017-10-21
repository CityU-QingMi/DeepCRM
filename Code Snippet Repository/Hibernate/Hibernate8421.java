	@Test 
	@TestForIssue( jiraKey = "")
	public void testInLineSynchWithIdentityColumn() {
		Session s = openSession();
		s.beginTransaction();
		ClassWithIdentityColumn e = new ClassWithIdentityColumn();
		e.setName("Dampf");
		s.save(e);
		e.setName("Klein");
		assertNotNull(session.bySimpleNaturalId(ClassWithIdentityColumn.class).load("Klein"));

		session.getTransaction().rollback();
		session.close();
	}

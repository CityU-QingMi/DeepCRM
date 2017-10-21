	@Test
	@TestForIssue(jiraKey = "")
	public void testUserTypeId() {
		Session s = openSession();
		s.beginTransaction();
		SomeEntity e1 = new SomeEntity();
		CustomId e1Id = new CustomId( 1L );
		e1.setCustomId( e1Id );
		SomeEntity e2 = new SomeEntity();
		CustomId e2Id = new CustomId( 2L );
		e2.setCustomId( e2Id );
		s.persist( e1 );
		s.persist( e2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		e1 = s.get( SomeEntity.class, e1Id );
		e2 = s.get( SomeEntity.class, e2Id );
		s.delete( e1 );
		s.delete( e2 );
		s.getTransaction().commit();
		s.close();
	}

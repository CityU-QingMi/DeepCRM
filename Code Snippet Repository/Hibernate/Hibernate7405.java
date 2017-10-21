	@Test
	@TestForIssue(jiraKey = "")
	public void testInitializedCompositesEmpty() {
		Session s = openSession();
		try {
			s.getTransaction().begin();

			ComponentEmptyEmbeddedOwner owner = new ComponentEmptyEmbeddedOwner();
			s.persist( owner );

			s.flush();
			s.getTransaction().commit();

			s.clear();
			s.getTransaction().begin();
			owner = (ComponentEmptyEmbeddedOwner) s.get( ComponentEmptyEmbeddedOwner.class, owner.getId() );
			assertNotNull( owner.getEmbedded() );
			assertFalse( s.isDirty() );

			s.getTransaction().rollback();
		}
		finally {
			s.close();
		}
	}

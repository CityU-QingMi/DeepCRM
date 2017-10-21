	@Test
	@TestForIssue(jiraKey = "")
	public void testCompositesEmpty() {
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
			assertNull( owner.getEmbedded() );
			owner.setEmbedded( new ComponentEmptyEmbedded() );

			// technically, as all properties are null, update may not be necessary
			assertFalse( session.isDirty() ); // must be false to avoid unnecessary updates

			s.getTransaction().rollback();
		}
		finally {
			s.close();
		}
	}

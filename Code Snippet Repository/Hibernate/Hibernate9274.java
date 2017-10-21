	@Test
	@TestForIssue(jiraKey = "")
	public void testRemoveAndReattachProxyEntity() {
		Session s = openSession();
		s.beginTransaction();
		Parent p = new Parent("foo");
		s.persist( p );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		p = (Parent) s.load( Parent.class, p.getName() );
		s.delete( p );
		// re-attach
		s.persist( p );
		s.getTransaction().commit();
		s.close();
	}

	@Test
	@TestForIssue( jiraKey = "" )
	public void testMapKeyColumnNonInsertableNonUpdatableUnidirOneToMany() {
		Session s = openSession();
		s.getTransaction().begin();
		User user = new User();
		Detail detail = new Detail();
		detail.description = "desc";
		detail.detailType = "trivial";
		user.details.put( detail.detailType, detail );
		s.persist( user );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		user = s.get( User.class, user.id );
		user.details.clear();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		user = s.get( User.class, user.id );
		s.delete( user );
		s.createQuery( "delete from " + User.class.getName() ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}

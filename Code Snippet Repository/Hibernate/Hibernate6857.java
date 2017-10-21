	@Test
	@TestForIssue( jiraKey = "" )
	public void testOrderByOnSuperclassProperty() {
		OrganisationUser user = new OrganisationUser();
		user.setFirstName( "Emmanuel" );
		user.setLastName( "Bernard" );
		user.setIdPerson( 1l );
		user.setSomeText( "SomeText" );
		Organisation org = new Organisation();
		org.setIdOrganisation( 1l );
		org.setName( "S Diego Zoo" );
		user.setOrganisation( org );
		Session s = openSession();
		s.getTransaction().begin();
		s.persist( user );
		s.persist( org );
		s.flush();
		s.clear();
		s.createQuery( "select org from Organisation org left join fetch org.organisationUsers" ).list();
		s.getTransaction().rollback();
		s.close();
	}

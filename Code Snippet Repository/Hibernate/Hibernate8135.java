	@Test
	@TestForIssue( jiraKey = "" )
	public void testClassAsParameter() {
		Session s = openSession();
		s.beginTransaction();

		s.createQuery( "from Human h where h.name = :class" ).setParameter( "class", new Name() ).list();
		s.createQuery( "from Human where name = :class" ).setParameter( "class", new Name() ).list();
		s.createQuery( "from Human h where :class = h.name" ).setParameter( "class", new Name() ).list();
		s.createQuery( "from Human h where :class <> h.name" ).setParameter( "class", new Name() ).list();

		s.getTransaction().commit();
		s.close();
	}

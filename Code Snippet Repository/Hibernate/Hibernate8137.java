	@Test
	@TestForIssue( jiraKey = "" )
	public void testObjectAsParameter() {
		Session s = openSession();
		s.beginTransaction();

		s.createQuery( "from Human h where h.name = :OBJECT" ).setParameter( "OBJECT", new Name() ).list();
		s.createQuery( "from Human where name = :OBJECT" ).setParameter( "OBJECT", new Name() ).list();
		s.createQuery( "from Human h where :OBJECT = h.name" ).setParameter( "OBJECT", new Name() ).list();
		s.createQuery( "from Human h where :OBJECT <> h.name" ).setParameter( "OBJECT", new Name() ).list();

		s.getTransaction().commit();
		s.close();
	}

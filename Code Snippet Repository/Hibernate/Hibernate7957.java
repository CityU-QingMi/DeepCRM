	@Test
	public void testJPAQLQualifiedIdentificationVariablesControl() {
		// just checking syntax here...
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from VariousKeywordPropertyEntity where type = 'something'" ).list();
		s.createQuery( "from VariousKeywordPropertyEntity where value = 'something'" ).list();
		s.createQuery( "from VariousKeywordPropertyEntity where key = 'something'" ).list();
		s.createQuery( "from VariousKeywordPropertyEntity where entry = 'something'" ).list();

		s.createQuery( "from VariousKeywordPropertyEntity e where e.type = 'something'" ).list();
		s.createQuery( "from VariousKeywordPropertyEntity e where e.value = 'something'" ).list();
		s.createQuery( "from VariousKeywordPropertyEntity e where e.key = 'something'" ).list();
		s.createQuery( "from VariousKeywordPropertyEntity e where e.entry = 'something'" ).list();
		s.getTransaction().commit();
		s.close();
	}

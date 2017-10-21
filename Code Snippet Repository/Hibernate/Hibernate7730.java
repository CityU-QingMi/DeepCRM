	@Override
	protected void cleanupTestData() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		List<Alias> aliases = s.createQuery( "from Alias" ).list();
		for ( Alias alias : aliases ) {
			for ( Character character : alias.getCharacters() ) {
				character.getAliases().clear();
			}
			alias.getCharacters().clear();
		}
		s.flush();
		s.createQuery( "delete Alias" ).executeUpdate();
		s.createQuery( "delete Character" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}

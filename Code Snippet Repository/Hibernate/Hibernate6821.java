	@Test
	@SkipForDialect(value = { HSQLDialect.class }, comment = "")
	public void testManyToOneToPkWithOnlyFormula() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Language language = new Language();
		language.setCode( "EN" );
		language.setName( "English" );
		s.persist( language );

		Message msg = new Message();
		msg.setLanguageCode( "en" );
		msg.setLanguageName( "English" );
		s.persist( msg );

		s.flush();
		s.clear();

		msg = ( Message ) s.get( Message.class, msg.getId() );
		assertNotNull( msg.getLanguage() );
		assertEquals( "EN", msg.getLanguage().getCode() );
		tx.rollback();
		s.close();
	}

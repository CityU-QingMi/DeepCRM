	@Test
	@TestForIssue( jiraKey = "")
	public void testMapKeyColumnInEmbeddableElement() {
		Session s = openSession();
		s.getTransaction().begin();
		MultilingualString m = new MultilingualString();
		LocalizedString localizedString = new LocalizedString();
		localizedString.setLanguage( "English" );
		localizedString.setText( "name" );
		m.getMap().put( localizedString.getLanguage(), localizedString );
		localizedString = new LocalizedString();
		localizedString.setLanguage( "English Pig Latin" );
		localizedString.setText( "amenay" );
		m.getMap().put( localizedString.getLanguage(), localizedString );
		s.persist( m );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		m = s.get( MultilingualString.class, m.getId());
		assertEquals( 2, m.getMap().size() );
		localizedString = m.getMap().get( "English" );
		assertEquals( "English", localizedString.getLanguage() );
		assertEquals( "name", localizedString.getText() );
		localizedString = m.getMap().get( "English Pig Latin" );
		assertEquals( "English Pig Latin", localizedString.getLanguage() );
		assertEquals( "amenay", localizedString.getText() );
		s.delete( m );
		s.getTransaction().commit();
		s.close();
	}

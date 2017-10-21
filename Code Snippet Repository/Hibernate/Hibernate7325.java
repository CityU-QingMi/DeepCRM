	@Test
	@TestForIssue( jiraKey = "")
	public void testJoinFetchElementCollectionWithParentSelect() {
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

		MultilingualStringParent parent = new MultilingualStringParent();
		parent.setString( m );

		s.persist( m );
		s.persist( parent );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		m = s.createQuery(
				"SELECT s FROM MultilingualStringParent parent " +
						"JOIN parent.string s " +
						"JOIN FETCH s.map", MultilingualString.class )
				.getSingleResult();
		assertEquals( 2, m.getMap().size() );
		localizedString = m.getMap().get( "English" );
		assertEquals( "English", localizedString.getLanguage() );
		assertEquals( "name", localizedString.getText() );
		localizedString = m.getMap().get( "English Pig Latin" );
		assertEquals( "English Pig Latin", localizedString.getLanguage() );
		assertEquals( "amenay", localizedString.getText() );
		s.delete( parent );
		s.delete( m );
		s.getTransaction().commit();
		s.close();
	}

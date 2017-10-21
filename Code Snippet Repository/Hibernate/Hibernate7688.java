	@Test
	@TestForIssue(jiraKey = "")
	public void hbmEnumTypeTest() {
		Session s = openSession();
		s.getTransaction().begin();
		s.persist( Person.person( Gender.MALE, HairColor.BROWN ) );
		s.persist( Person.person( Gender.MALE, HairColor.BLACK ) );
		s.persist( Person.person( Gender.FEMALE, HairColor.BROWN ) );
		s.persist( Person.person( Gender.FEMALE, HairColor.BLACK ) );
		s.getTransaction().commit();
		s.clear();

		s.getTransaction().begin();
		assertEquals(s.createCriteria( Person.class )
				.add( Restrictions.eq( "gender", Gender.MALE ) )
				.list().size(), 2);
		assertEquals(s.createCriteria( Person.class )
				.add( Restrictions.eq( "gender", Gender.MALE ) )
				.add( Restrictions.eq( "hairColor", HairColor.BROWN ) )
				.list().size(), 1);
		assertEquals(s.createCriteria( Person.class )
				.add( Restrictions.eq( "gender", Gender.FEMALE ) )
				.list().size(), 2);
		assertEquals(s.createCriteria( Person.class )
				.add( Restrictions.eq( "gender", Gender.FEMALE ) )
				.add( Restrictions.eq( "hairColor", HairColor.BROWN ) )
				.list().size(), 1);
		s.getTransaction().commit();
		s.close();
	}

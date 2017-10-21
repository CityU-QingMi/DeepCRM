	@Test
	@TestForIssue( jiraKey = "" )
	public void testVarargJunctionSyntax() {
		Session session = openSession();
		session.beginTransaction();
		session.createCriteria( Course.class )
				.add(
						Restrictions.and(
								Property.forName( "description" ).like( "Hibernate%" ),
								Property.forName( "description" ).like( "%ORM%" )
						)
				)
				.list();

		session.createCriteria( Course.class )
				.add(
						Restrictions.and(
								Property.forName( "description" ).like( "Hibernate%" ),
								Restrictions.or(
										Property.forName( "description" ).like( "%ORM%" ),
										Property.forName( "description" ).like( "%Search%" )
								)

						)
				)
				.list();

		session.getTransaction().commit();
		session.close();
	}

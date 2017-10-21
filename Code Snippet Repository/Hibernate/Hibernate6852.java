	@Test
	public void testHqlDefaultNullOrdering() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Monkey monkey1 = new Monkey();
		monkey1.setName( null );
		Monkey monkey2 = new Monkey();
		monkey2.setName( "Warsaw ZOO" );
		session.persist( monkey1 );
		session.persist( monkey2 );
		session.getTransaction().commit();

		session.getTransaction().begin();
		List<Zoo> orderedResults = (List<Zoo>) session.createQuery( "from Monkey m order by m.name" ).list(); // Should order by NULLS LAST.
		Assert.assertEquals( Arrays.asList( monkey2, monkey1 ), orderedResults );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( monkey1 );
		session.delete( monkey2 );
		session.getTransaction().commit();

		session.close();
	}

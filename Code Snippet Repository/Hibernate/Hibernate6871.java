	public void testHqlNullsFirstLast() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Zoo zoo1 = new Zoo();
		zoo1.setName( null );
		Zoo zoo2 = new Zoo();
		zoo2.setName( "Warsaw ZOO" );
		session.persist( zoo1 );
		session.persist( zoo2 );
		session.getTransaction().commit();

		session.getTransaction().begin();
		List<Zoo> orderedResults = (List<Zoo>) session.createQuery( "from Zoo z order by z.name nulls lAsT" ).list();
		Assert.assertEquals( Arrays.asList( zoo2, zoo1 ), orderedResults );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( zoo1 );
		session.delete( zoo2 );
		session.getTransaction().commit();

		session.close();
	}

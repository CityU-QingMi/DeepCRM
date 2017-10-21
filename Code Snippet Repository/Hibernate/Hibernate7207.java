	@Test
	public void testCollectionCacheEvictionInsert() {
		People people = createPeople();
		people = initCache( people.id );

		Session session = openSession();
		session.beginTransaction();

		people = session.get( People.class, people.id );
		Person person = new Person();
		session.save( person );
		people.people.add( person );

		session.getTransaction().commit();
		session.close();

		session = openSession();

		people = session.get( People.class, people.id );
		assertEquals( 3, people.people.size() );

		session.close();
	}

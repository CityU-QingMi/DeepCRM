	@Test
	public void testCollectionCacheEvictionRemove() {
		People people = createPeople();
		people = initCache( people.id );

		Session session = openSession();
		session.beginTransaction();

		people = session.get( People.class, people.id );
		Person person = people.people.remove( 0 );
		session.delete( person );

		session.getTransaction().commit();
		session.close();

		session = openSession();

		people = session.get( People.class, people.id );
		assertEquals( 1, people.people.size() );

		session.close();
	}

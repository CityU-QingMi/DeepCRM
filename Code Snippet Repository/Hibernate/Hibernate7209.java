	@Test
	public void testCollectionCacheEvictionUpdate() {
		People people1 = createPeople();
		people1 = initCache( people1.id );
		People people2 = createPeople();
		people2 = initCache( people2.id );


		Session session = openSession();
		session.beginTransaction();

		people1 = session.get( People.class, people1.id );
		people2 = session.get( People.class, people2.id );

		Person person1 = people1.people.remove( 0 );
		Person person2 = people1.people.remove( 0 );
		Person person3 = people2.people.remove( 0 );
		session.flush();//avoid: Unique index or primary key violation
		people1.people.add( person3 );
		people2.people.add( person2 );
		people2.people.add( person1 );

		session.getTransaction().commit();
		session.close();

		session = openSession();

		people1 = session.get( People.class, people1.id );
		people2 = session.get( People.class, people2.id );
		assertEquals( 1, people1.people.size() );
		assertEquals( 3, people2.people.size() );

		session.close();
	}

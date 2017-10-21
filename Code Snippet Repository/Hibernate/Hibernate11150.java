	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getOrCreateEntityManager();
		try {
			// Revision 1
			em.getTransaction().begin();
			NameInfo ni = new NameInfo( "John", "Doe" );
			Person person1 = new Person( "JDOE", ni );
			em.persist( person1 );
			em.getTransaction().commit();

			// Revision 2
			em.getTransaction().begin();
			person1 = em.find( Person.class, person1.getId() );
			person1.getNameInfo().setFirstName( "Jane" );
			em.merge( person1 );
			em.getTransaction().commit();

			// Revision 3
			em.getTransaction().begin();
			person1 = em.find( Person.class, person1.getId() );
			person1.setName( "JDOE2" );
			em.merge( person1 );
			em.getTransaction().commit();

			personId = person1.getId();
		}
		finally {
			em.close();
		}
	}

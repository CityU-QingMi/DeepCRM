	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1 - empty element collection
		em.getTransaction().begin();
		DarkCharacter darkCharacter = new DarkCharacter( 1, 1 );
		em.persist( darkCharacter );
		em.getTransaction().commit();

		id = darkCharacter.getId();

		// Revision 2 - adding collection element
		em.getTransaction().begin();
		darkCharacter = em.find( DarkCharacter.class, darkCharacter.getId() );
		darkCharacter.getNames().add( new Name( "Action", "Hank" ) );
		darkCharacter = em.merge( darkCharacter );
		em.getTransaction().commit();

		// Revision 3 - adding another collection element
		em.getTransaction().begin();
		darkCharacter = em.find( DarkCharacter.class, darkCharacter.getId() );
		darkCharacter.getNames().add( new Name( "Green", "Lantern" ) );
		darkCharacter = em.merge( darkCharacter );
		em.getTransaction().commit();

		// Revision 4 - removing single collection element
		em.getTransaction().begin();
		darkCharacter = em.find( DarkCharacter.class, darkCharacter.getId() );
		darkCharacter.getNames().remove( new Name( "Action", "Hank" ) );
		darkCharacter = em.merge( darkCharacter );
		em.getTransaction().commit();

		// Revision 5 - removing all collection elements
		em.getTransaction().begin();
		darkCharacter = em.find( DarkCharacter.class, darkCharacter.getId() );
		darkCharacter.getNames().clear();
		darkCharacter = em.merge( darkCharacter );
		em.getTransaction().commit();

		em.close();
	}

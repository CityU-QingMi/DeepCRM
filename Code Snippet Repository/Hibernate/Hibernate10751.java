	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		OneToManyOwned owned = new OneToManyOwned( "data", null );
		Set<ManyToOneOwning> referencing = new HashSet<ManyToOneOwning>();
		ManyToOneOwning owning1 = new ManyToOneOwning( "data1", owned );
		referencing.add( owning1 );
		ManyToOneOwning owning2 = new ManyToOneOwning( "data2", owned );
		referencing.add( owning2 );
		owned.setReferencing( referencing );

		// Revision 1
		em.getTransaction().begin();
		em.persist( owned );
		em.persist( owning1 );
		em.persist( owning2 );
		em.getTransaction().commit();

		ownedId = owned.getId();
		owning1Id = owning1.getId();
		owning2Id = owning2.getId();

		// Revision 2
		em.getTransaction().begin();
		owning1 = em.find( ManyToOneOwning.class, owning1.getId() );
		em.remove( owning1 );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		owning2 = em.find( ManyToOneOwning.class, owning2.getId() );
		owning2.setData( "data2modified" );
		em.merge( owning2 );
		em.getTransaction().commit();
	}

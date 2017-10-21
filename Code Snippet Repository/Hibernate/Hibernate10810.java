	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		StringSetEntity sse1 = new StringSetEntity();
		StringSetEntity sse2 = new StringSetEntity();

		// Revision 1 (sse1: initialy empty, sse2: initialy 2 elements)
		em.getTransaction().begin();

		sse2.getStrings().add( "sse2_string1" );
		sse2.getStrings().add( "sse2_string2" );

		em.persist( sse1 );
		em.persist( sse2 );

		em.getTransaction().commit();

		// Revision 2 (sse1: adding 2 elements, sse2: adding an existing element)
		em.getTransaction().begin();

		sse1 = em.find( StringSetEntity.class, sse1.getId() );
		sse2 = em.find( StringSetEntity.class, sse2.getId() );

		sse1.getStrings().add( "sse1_string1" );
		sse1.getStrings().add( "sse1_string2" );

		sse2.getStrings().add( "sse2_string1" );

		em.getTransaction().commit();

		// Revision 3 (sse1: removing a non-existing element, sse2: removing one element)
		em.getTransaction().begin();

		sse1 = em.find( StringSetEntity.class, sse1.getId() );
		sse2 = em.find( StringSetEntity.class, sse2.getId() );

		sse1.getStrings().remove( "sse1_string3" );
		sse2.getStrings().remove( "sse2_string1" );

		em.getTransaction().commit();

		//

		sse1_id = sse1.getId();
		sse2_id = sse2.getId();
	}

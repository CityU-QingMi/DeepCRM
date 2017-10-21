	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		StringListEntity sle1 = new StringListEntity();
		StringListEntity sle2 = new StringListEntity();

		// Revision 1 (sle1: initialy empty, sle2: initialy 2 elements)
		em.getTransaction().begin();

		sle2.getStrings().add( "sle2_string1" );
		sle2.getStrings().add( "sle2_string2" );

		em.persist( sle1 );
		em.persist( sle2 );

		em.getTransaction().commit();

		// Revision 2 (sle1: adding 2 elements, sle2: adding an existing element)
		em.getTransaction().begin();

		sle1 = em.find( StringListEntity.class, sle1.getId() );
		sle2 = em.find( StringListEntity.class, sle2.getId() );

		sle1.getStrings().add( "sle1_string1" );
		sle1.getStrings().add( "sle1_string2" );

		sle2.getStrings().add( "sle2_string1" );

		em.getTransaction().commit();

		// Revision 3 (sle1: replacing an element at index 0, sle2: removing an element at index 0)
		em.getTransaction().begin();

		sle1 = em.find( StringListEntity.class, sle1.getId() );
		sle2 = em.find( StringListEntity.class, sle2.getId() );

		sle1.getStrings().set( 0, "sle1_string3" );

		sle2.getStrings().remove( 0 );

		em.getTransaction().commit();

		//

		sle1_id = sle1.getId();
		sle2_id = sle2.getId();
	}

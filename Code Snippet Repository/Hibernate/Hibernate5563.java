	@Test
	public void testDetach() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		Tooth tooth = new Tooth();
		Mouth mouth = new Mouth();
		em.persist( mouth );
		em.persist( tooth );
		tooth.mouth = mouth;
		mouth.teeth = new ArrayList<Tooth>();
		mouth.teeth.add( tooth );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		mouth = em.find( Mouth.class, mouth.id );
		assertNotNull( mouth );
		assertEquals( 1, mouth.teeth.size() );
		tooth = mouth.teeth.iterator().next();
		em.detach( mouth );
		assertFalse( em.contains( tooth ) );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.remove( em.find( Mouth.class, mouth.id ) );

		em.getTransaction().commit();
		em.close();
	}

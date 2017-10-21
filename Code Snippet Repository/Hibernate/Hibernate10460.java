	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		IdMapKeyEntity imke = new IdMapKeyEntity();

		// Revision 1 (intialy 1 mapping)
		em.getTransaction().begin();

		StrTestEntity ste1 = new StrTestEntity( "x" );
		StrTestEntity ste2 = new StrTestEntity( "y" );

		em.persist( ste1 );
		em.persist( ste2 );

		imke.getIdmap().put( ste1.getId(), ste1 );

		em.persist( imke );

		em.getTransaction().commit();

		// Revision 2 (sse1: adding 1 mapping)
		em.getTransaction().begin();

		ste2 = em.find( StrTestEntity.class, ste2.getId() );
		imke = em.find( IdMapKeyEntity.class, imke.getId() );

		imke.getIdmap().put( ste2.getId(), ste2 );

		em.getTransaction().commit();

		//

		imke_id = imke.getId();

		ste1_id = ste1.getId();
		ste2_id = ste2.getId();
	}

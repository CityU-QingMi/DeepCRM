	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1 (intialy 1 relation: ing1 -> ed)
		em.getTransaction().begin();

		RefEdMapKeyEntity ed = new RefEdMapKeyEntity();

		em.persist( ed );

		RefIngMapKeyEntity ing1 = new RefIngMapKeyEntity();
		ing1.setData( "a" );
		ing1.setReference( ed );

		RefIngMapKeyEntity ing2 = new RefIngMapKeyEntity();
		ing2.setData( "b" );

		em.persist( ing1 );
		em.persist( ing2 );

		em.getTransaction().commit();

		// Revision 2 (adding second relation: ing2 -> ed)
		em.getTransaction().begin();

		ed = em.find( RefEdMapKeyEntity.class, ed.getId() );
		ing2 = em.find( RefIngMapKeyEntity.class, ing2.getId() );

		ing2.setReference( ed );

		em.getTransaction().commit();

		//

		ed_id = ed.getId();

		ing1_id = ing1.getId();
		ing2_id = ing2.getId();
	}

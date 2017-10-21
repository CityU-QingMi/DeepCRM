	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Rev 1
		em.getTransaction().begin();

		ReferencedEntity re1 = new ReferencedEntity();
		em.persist( re1 );
		re_id1 = re1.getId();

		ReferencedEntity re2 = new ReferencedEntity();
		em.persist( re2 );
		re_id2 = re2.getId();

		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();

		re1 = em.find( ReferencedEntity.class, re_id1 );

		ChildIngEntity cie = new ChildIngEntity( "y", 1l );
		cie.setReferenced( re1 );
		em.persist( cie );
		c_id = cie.getId();

		em.getTransaction().commit();

		// Rev 3
		em.getTransaction().begin();

		re2 = em.find( ReferencedEntity.class, re_id2 );
		cie = em.find( ChildIngEntity.class, c_id );

		cie.setReferenced( re2 );

		em.getTransaction().commit();
	}

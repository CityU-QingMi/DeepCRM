	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		ed_id1 = 1;
		p_id = 10;
		c_id = 100;

		// Rev 1
		em.getTransaction().begin();

		ReferencedEntity re = new ReferencedEntity( ed_id1 );
		em.persist( re );

		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();

		re = em.find( ReferencedEntity.class, ed_id1 );

		ParentIngEntity pie = new ParentIngEntity( p_id, "x" );
		pie.setReferenced( re );
		em.persist( pie );
		p_id = pie.getId();

		em.getTransaction().commit();

		// Rev 3
		em.getTransaction().begin();

		re = em.find( ReferencedEntity.class, ed_id1 );

		ChildIngEntity cie = new ChildIngEntity( c_id, "y", 1l );
		cie.setReferenced( re );
		em.persist( cie );
		c_id = cie.getId();

		em.getTransaction().commit();
	}

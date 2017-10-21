	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Rev 1
		em.getTransaction().begin();

		ReferencedEntity re = new ReferencedEntity();
		em.persist( re );
		ed_id1 = re.getId();

		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();

		re = em.find( ReferencedEntity.class, ed_id1 );

		ParentIngEntity pie = new ParentIngEntity( "x" );
		pie.setReferenced( re );
		em.persist( pie );
		p_id = pie.getId();

		em.getTransaction().commit();

		// Rev 3
		em.getTransaction().begin();

		re = em.find( ReferencedEntity.class, ed_id1 );

		ChildIngEntity cie = new ChildIngEntity( "y", 1l );
		cie.setReferenced( re );
		em.persist( cie );
		c_id = cie.getId();

		em.getTransaction().commit();
	}

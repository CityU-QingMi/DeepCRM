	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		AuditedImplementor ai = new AuditedImplementor();
		ai.setData( "La data" );
		ai.setAuditedImplementorData( "audited implementor data" );

		NonAuditedImplementor nai = new NonAuditedImplementor();
		nai.setData( "info" );
		nai.setNonAuditedImplementorData( "sttring" );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ai );

		em.persist( nai );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ai = em.find( AuditedImplementor.class, ai.getId() );
		nai = em.find( NonAuditedImplementor.class, nai.getId() );

		ai.setData( "La data 2" );
		ai.setAuditedImplementorData( "audited implementor data 2" );

		nai.setData( "info 2" );
		nai.setNonAuditedImplementorData( "sttring 2" );

		em.getTransaction().commit();

		//

		ai_id = ai.getId();
		nai_id = nai.getId();
	}

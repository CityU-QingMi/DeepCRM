	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		AuditedImplementor ai = new AuditedImplementor();
		ai.setData( "La data" );
		ai.setAuditedImplementorData( "audited implementor data" );
		ai.setNumerito( NUMERITO );

		NonAuditedImplementor nai = new NonAuditedImplementor();
		nai.setData( "info" );
		nai.setNonAuditedImplementorData( "sttring" );
		nai.setNumerito( NUMERITO );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ai );

		em.persist( nai );

		em.getTransaction().commit();

		// Revision 2

		// Revision 3

		ai_id = ai.getId();
		nai_id = nai.getId();
	}

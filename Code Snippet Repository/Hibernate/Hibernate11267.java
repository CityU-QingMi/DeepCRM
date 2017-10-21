	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		NotAuditedSubclassEntity nas = new NotAuditedSubclassEntity( "nae", "super str", "not audited str" );
		em.persist( nas );
		AuditedMethodSubclassEntity ae = new AuditedMethodSubclassEntity( "ae", "super str", "audited str" );
		em.persist( ae );
		id1_1 = ae.getId();
		id2_1 = nas.getId();
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		ae = em.find( AuditedMethodSubclassEntity.class, id1_1 );
		ae.setStr( "ae new" );
		ae.setSubAuditedStr( "audited str new" );
		nas = em.find( NotAuditedSubclassEntity.class, id2_1 );
		nas.setStr( "nae new" );
		nas.setNotAuditedStr( "not aud str new" );
		em.getTransaction().commit();
	}

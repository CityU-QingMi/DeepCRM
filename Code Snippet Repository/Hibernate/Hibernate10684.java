	@Test
	public void testRetrieveAudited() {
		// levanto las versiones actuales
		AuditedImplementor ai = getEntityManager().find( AuditedImplementor.class, ai_id );
		assert ai != null;
		SimpleInterface si = getEntityManager().find( SimpleInterface.class, ai_id );
		assert si != null;

		// levanto las de la revisi�n 1, ninguna debe ser null
		AuditedImplementor ai_rev1 = getAuditReader().find( AuditedImplementor.class, ai_id, 1 );
		assert ai_rev1 != null;
		SimpleInterface si_rev1 = getAuditReader().find( SimpleInterface.class, ai_id, 1 );
		assert si_rev1 != null;

		AuditedImplementor ai_rev2 = getAuditReader().find( AuditedImplementor.class, ai_id, 2 );
		assert ai_rev2 != null;
		SimpleInterface si_rev2 = getAuditReader().find( SimpleInterface.class, ai_id, 2 );
		assert si_rev2 != null;

		// data de las actuales no debe ser null
		Assert.assertEquals( ai.getData(), "La data 2" );
		Assert.assertEquals( si.getData(), "La data 2" );
		// la data de las revisiones no debe ser null
		Assert.assertEquals( ai_rev1.getData(), "La data" );
		Assert.assertEquals( si_rev1.getData(), "La data" );

		Assert.assertEquals( ai_rev2.getData(), "La data 2" );
		Assert.assertEquals( si_rev2.getData(), "La data 2" );
	}

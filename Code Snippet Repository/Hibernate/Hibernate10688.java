	@Test
	public void testRetrieveAudited() {
		// levanto las versiones actuales
		AuditedImplementor ai = getEntityManager().find(
				AuditedImplementor.class, ai_id
		);
		assert ai != null;
		SimpleInterface si = getEntityManager().find(
				SimpleInterface.class,
				ai_id
		);
		assert si != null;

		// levanto las de la revisi�n 1, ninguna debe ser null
		AuditedImplementor ai_rev1 = getAuditReader().find(
				AuditedImplementor.class, ai_id, 1
		);
		assert ai_rev1 != null;
		SimpleInterface si_rev1 = getAuditReader().find(
				SimpleInterface.class,
				ai_id, 1
		);
		assert si_rev1 != null;

		// data de las actuales no debe ser null
		assert ai.getData() != null;
		assert si.getData() != null;
		// data de las revisiones No est� auditada
		assert ai_rev1.getData() == null;
		assert si_rev1.getData() == null;
		// numerito de las revisiones est� auditada, debe ser igual a NUMERITO
		assert ai_rev1.getNumerito() == NUMERITO;
		assert si_rev1.getNumerito() == NUMERITO;
	}

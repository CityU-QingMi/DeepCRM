	@Test
	public void testRetrieveNonAudited() {
		// levanto las versiones actuales
		NonAuditedImplementor nai = getEntityManager().find(
				NonAuditedImplementor.class, nai_id
		);
		assert nai != null;
		SimpleInterface si = getEntityManager().find(
				SimpleInterface.class,
				nai_id
		);
		assert si != null;

		assert si.getData().equals( nai.getData() );

		try {
			// levanto la revision
			getAuditReader().find( NonAuditedImplementor.class, nai_id, 1 );
			assert false;
		}
		catch (Exception e) {
			// no es auditable!!!
			assert (e instanceof NotAuditedException);
		}

		// levanto la revision que no es auditable pero con la interfaz, el
		// resultado debe ser null
		SimpleInterface si_rev1 = getAuditReader().find(
				SimpleInterface.class,
				nai_id, 1
		);
		assert si_rev1 == null;
	}

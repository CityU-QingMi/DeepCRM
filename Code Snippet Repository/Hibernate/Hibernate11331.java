	private void checkEmptyAuditSessionCache(Session session, String... auditEntityNames) {
		List<String> entityNames = Arrays.asList( auditEntityNames );
		PersistenceContext persistenceContext = ((SessionImplementor) session).getPersistenceContext();
		for ( Map.Entry<Object, EntityEntry> entrySet : persistenceContext.reentrantSafeEntityEntries() ) {
			final EntityEntry entityEntry = entrySet.getValue();
			if ( entityNames.contains( entityEntry.getEntityName() ) ) {
				assert false : "Audit data shall not be stored in the session level cache. This causes performance issues.";
			}
			Assert.assertFalse(
					"Revision entity shall not be stored in the session level cache. This causes performance issues.",
					SequenceIdRevisionEntity.class.getName().equals( entityEntry.getEntityName() )
			);
		}
	}

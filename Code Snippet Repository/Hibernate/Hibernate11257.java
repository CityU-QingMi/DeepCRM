	private List<Map<String, Object>> getRevisions(
			Class<?> originalEntityClazz, Integer originalEntityId) {
		// Build the query:
		// select auditEntity from
		// org.hibernate.envers.test.entities.manytomany.sametable.ParentEntity_AUD
		// auditEntity where auditEntity.originalId.id = :originalEntityId

		StringBuilder builder = new StringBuilder( "select auditEntity from " );
		builder.append( originalEntityClazz.getName() )
				.append( "_AUD auditEntity" );
		builder.append( " where auditEntity.originalId.id = :originalEntityId" );

		Query qry = getEntityManager().createQuery( builder.toString() );
		qry.setParameter( "originalEntityId", originalEntityId );

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> resultList = qry.getResultList();
		return resultList;
	}

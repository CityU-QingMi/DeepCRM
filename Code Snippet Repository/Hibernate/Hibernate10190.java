	@Override
	public AuditAssociationQuery<? extends AuditQuery> traverseRelation(String associationName, JoinType joinType, String alias) {
		AuditAssociationQueryImpl<AuditQueryImplementor> result = associationQueryMap.get( associationName );
		if (result == null) {
			result = new AuditAssociationQueryImpl<>(
					enversService,
					versionsReader,
					this,
					qb,
					associationName,
					joinType,
					aliasToEntityNameMap,
					REFERENCED_ENTITY_ALIAS,
					alias
			);
			associationQueries.add( result );
			associationQueryMap.put( associationName, result );
		}
		return result;
	}

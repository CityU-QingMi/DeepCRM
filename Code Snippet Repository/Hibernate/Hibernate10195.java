	@Override
	public AuditAssociationQueryImpl<AuditAssociationQueryImpl<Q>> traverseRelation(
			String associationName,
			JoinType joinType,
			String alias) {
		AuditAssociationQueryImpl<AuditAssociationQueryImpl<Q>> result = associationQueryMap.get( associationName );
		if ( result == null ) {
			result = new AuditAssociationQueryImpl<>(
					enversService,
					auditReader,
					this,
					queryBuilder,
					associationName,
					joinType,
					aliasToEntityNameMap,
					this.alias,
					alias
			);
			associationQueries.add( result );
			associationQueryMap.put( associationName, result );
		}
		return result;
	}

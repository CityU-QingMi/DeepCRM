	@Override
	public AuditAssociationQueryImpl<AuditAssociationQueryImpl<Q>> traverseRelation(
			String associationName,
			JoinType joinType) {
		return traverseRelation(
				associationName,
				joinType,
				null
		);
	}

	@Override
	public AuditAssociationQueryImpl<Q> addOrder(AuditOrder order) {
		AuditOrder.OrderData orderData = order.getData( enversService );
		String orderEntityAlias = orderData.getAlias( alias );
		String orderEntityName = aliasToEntityNameMap.get( orderEntityAlias );
		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				auditReader,
				orderEntityName,
				orderData.getPropertyName()
		);
		queryBuilder.addOrder( orderEntityAlias, propertyName, orderData.isAscending() );
		return this;
	}

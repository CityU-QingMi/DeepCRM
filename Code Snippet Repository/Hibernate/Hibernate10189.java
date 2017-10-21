	public AuditQuery addOrder(AuditOrder order) {
		hasOrder = true;
		AuditOrder.OrderData orderData = order.getData( enversService );
		String orderEntityAlias = orderData.getAlias( REFERENCED_ENTITY_ALIAS );
		String orderEntityName = aliasToEntityNameMap.get( orderEntityAlias );
		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				versionsReader,
				orderEntityName,
				orderData.getPropertyName()
		);
		qb.addOrder( orderEntityAlias, propertyName, orderData.isAscending() );
		return this;
	}

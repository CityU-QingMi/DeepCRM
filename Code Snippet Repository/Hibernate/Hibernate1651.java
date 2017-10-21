	@Override
	public final String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final String entityName = criteriaQuery.getEntityName( criteria, propertyName );
		final String actualPropertyName = criteriaQuery.getPropertyName( propertyName );
		final String sqlAlias = criteriaQuery.getSQLAlias( criteria, propertyName );

		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		final QueryableCollection collectionPersister = getQueryableCollection( entityName, actualPropertyName, factory );

		final String[] collectionKeys = collectionPersister.getKeyColumnNames();
		final String[] ownerKeys = ( (Loadable) factory.getEntityPersister( entityName ) ).getIdentifierColumnNames();

		final String innerSelect = "(select 1 from " + collectionPersister.getTableName() + " where "
				+ new ConditionFragment().setTableAlias( sqlAlias ).setCondition( ownerKeys, collectionKeys ).toFragmentString()
				+ ")";

		return excludeEmpty()
				? "exists " + innerSelect
				: "not exists " + innerSelect;
	}

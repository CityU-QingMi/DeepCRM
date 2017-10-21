	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final String entityName =criteriaQuery.getEntityName( criteria, propertyName );
		final String role = entityName + '.' + criteriaQuery.getPropertyName( propertyName );
		final QueryableCollection cp = (QueryableCollection) criteriaQuery.getFactory().getCollectionPersister( role );

		final String[] fk = cp.getKeyColumnNames();
		final String[] pk = ( (Loadable) cp.getOwnerEntityPersister() ).getIdentifierColumnNames();

		final ConditionFragment subQueryRestriction = new ConditionFragment()
				.setTableAlias( criteriaQuery.getSQLAlias( criteria, propertyName ) )
				.setCondition( pk, fk );

		return String.format(
				Locale.ROOT,
				"? %s (select count(*) from %s where %s)",
				op,
				cp.getTableName(),
				subQueryRestriction.toFragmentString()
		);
	}

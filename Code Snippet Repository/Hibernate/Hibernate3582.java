	public CriteriaQueryTranslator(
			final SessionFactoryImplementor factory,
			final CriteriaImpl criteria,
			final String rootEntityName,
			final String rootSQLAlias) throws HibernateException {
		this.rootCriteria = criteria;
		this.rootEntityName = rootEntityName;
		this.sessionFactory = factory;
		this.rootSQLAlias = rootSQLAlias;
		this.helper = new SessionFactoryHelper( factory );
		createAliasCriteriaMap();
		createAssociationPathCriteriaMap();
		createCriteriaEntityNameMap();
		createCriteriaSQLAliasMap();
	}

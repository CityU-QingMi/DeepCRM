	public AbstractIdsBulkIdHandler(
			SessionFactoryImplementor sessionFactory, HqlSqlWalker walker) {
		super(sessionFactory, walker);

		final AbstractRestrictableStatement statement = (AbstractRestrictableStatement) walker.getAST();
		final FromElement fromElement = statement.getFromClause().getFromElement();

		this.targetedPersister = fromElement.getQueryable();

		final ProcessedWhereClause processedWhereClause = processWhereClause( statement.getWhereClause() );
		this.idSelectParameterSpecifications = processedWhereClause.getIdSelectParameterSpecifications();

		final String bulkTargetAlias = fromElement.getTableAlias();

		this.idSelect = generateIdSelect( bulkTargetAlias, processedWhereClause ).toStatementString();
	}

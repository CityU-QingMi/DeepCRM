	public CriteriaLoader(
			final OuterJoinLoadable persister,
			final SessionFactoryImplementor factory,
			final CriteriaImpl criteria,
			final String rootEntityName,
			final LoadQueryInfluencers loadQueryInfluencers) throws HibernateException {
		super( factory, loadQueryInfluencers );

		translator = new CriteriaQueryTranslator(
				factory,
				criteria,
				rootEntityName,
				CriteriaQueryTranslator.ROOT_SQL_ALIAS
		);

		querySpaces = translator.getQuerySpaces();

		CriteriaJoinWalker walker = new CriteriaJoinWalker(
				persister,
				translator,
				factory,
				criteria,
				rootEntityName,
				loadQueryInfluencers
		);

		initFromWalker( walker );

		userAliases = walker.getUserAliases();
		resultTypes = walker.getResultTypes();
		includeInResultRow = walker.includeInResultRow();
		resultRowLength = ArrayHelper.countTrue( includeInResultRow );

		postInstantiate();

	}

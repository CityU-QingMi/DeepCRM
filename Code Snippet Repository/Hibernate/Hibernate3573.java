	public CriteriaJoinWalker(
			final OuterJoinLoadable persister,
			final CriteriaQueryTranslator translator,
			final SessionFactoryImplementor factory,
			final CriteriaImpl criteria,
			final String rootEntityName,
			final LoadQueryInfluencers loadQueryInfluencers,
			final String alias) {
		super( persister, factory, loadQueryInfluencers, alias );

		this.translator = translator;

		querySpaces = translator.getQuerySpaces();

		if ( translator.hasProjection() ) {
			initProjection(
					translator.getSelect(),
					translator.getWhereCondition(),
					translator.getOrderBy(),
					translator.getGroupBy(),
					LockOptions.NONE
			);
			resultTypes = translator.getProjectedTypes();
			userAliases = translator.getProjectedAliases();
			includeInResultRow = new boolean[resultTypes.length];
			Arrays.fill( includeInResultRow, true );
		}
		else {
			initAll( translator.getWhereCondition(), translator.getOrderBy(), LockOptions.NONE );
			// root entity comes last
			userAliasList.add( criteria.getAlias() ); //root entity comes *last*
			resultTypeList.add( translator.getResultType( criteria ) );
			includeInResultRowList.add( true );
			userAliases = ArrayHelper.toStringArray( userAliasList );
			resultTypes = ArrayHelper.toTypeArray( resultTypeList );
			includeInResultRow = ArrayHelper.toBooleanArray( includeInResultRowList );
		}
	}

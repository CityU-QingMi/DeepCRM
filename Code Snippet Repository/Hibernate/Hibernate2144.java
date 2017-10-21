	public NormalizingIdentifierHelperImpl(
			JdbcEnvironment jdbcEnvironment,
			NameQualifierSupport nameQualifierSupport,
			boolean globallyQuoteIdentifiers,
			boolean globallyQuoteIdentifiersSkipColumnDefinitions,
			boolean autoQuoteKeywords,
			Set<String> reservedWords,
			IdentifierCaseStrategy unquotedCaseStrategy,
			IdentifierCaseStrategy quotedCaseStrategy) {
		this.jdbcEnvironment = jdbcEnvironment;
		this.nameQualifierSupport = nameQualifierSupport;
		this.globallyQuoteIdentifiers = globallyQuoteIdentifiers;
		this.globallyQuoteIdentifiersSkipColumnDefinitions = globallyQuoteIdentifiersSkipColumnDefinitions;
		this.autoQuoteKeywords = autoQuoteKeywords;
		if ( reservedWords != null ) {
			this.reservedWords.addAll( reservedWords );
		}
		this.unquotedCaseStrategy = unquotedCaseStrategy == null ? IdentifierCaseStrategy.UPPER : unquotedCaseStrategy;
		this.quotedCaseStrategy = quotedCaseStrategy == null ? IdentifierCaseStrategy.MIXED : quotedCaseStrategy;
	}

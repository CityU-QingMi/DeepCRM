	public IdentifierHelper build() {
		if ( unquotedCaseStrategy == quotedCaseStrategy ) {
			log.debugf(
					"IdentifierCaseStrategy for both quoted and unquoted identifiers was set " +
							"to the same strategy [%s]; that will likely lead to problems in schema update " +
							"and validation if using quoted identifiers",
					unquotedCaseStrategy.name()
			);
		}

		return new NormalizingIdentifierHelperImpl(
				jdbcEnvironment,
				nameQualifierSupport,
				globallyQuoteIdentifiers,
				skipGlobalQuotingForColumnDefinitions,
				autoQuoteKeywords,
				reservedWords,
				unquotedCaseStrategy,
				quotedCaseStrategy
		);
	}

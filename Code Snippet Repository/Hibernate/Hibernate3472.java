	private String[] determineKeyAlias(Loadable persister, String suffix) {
		final String[] aliases;
		final String[] keyColumnsCandidates = getUserProvidedAliases( persister.getIdentifierPropertyName(), null );
		if ( keyColumnsCandidates == null ) {
			aliases = getUserProvidedAliases(
					"id",
					getIdentifierAliases(persister, suffix)
			);
		}
		else {
			aliases = keyColumnsCandidates;
		}
		final String[] rtn = StringHelper.unquote( aliases, persister.getFactory().getDialect() );
		intern( rtn );
		return rtn;
	}

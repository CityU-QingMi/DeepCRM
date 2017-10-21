	public DefaultEntityAliases(
			Map userProvidedAliases,
			Loadable persister,
			String suffix) {
		this.suffix = suffix.intern();
		this.userProvidedAliases = userProvidedAliases;

		suffixedKeyColumns = determineKeyAlias( persister, suffix );
		suffixedPropertyColumns = determinePropertyAliases( persister );
		suffixedDiscriminatorColumn = determineDiscriminatorAlias( persister, suffix );
		suffixedVersionColumn = determineVersionAlias( persister );
		rowIdAlias = (Loadable.ROWID_ALIAS + suffix).intern(); // TODO: not visible to the user!
	}

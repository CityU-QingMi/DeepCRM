	@Override
	public String getAlias(Dialect dialect) {
		final int lastLetter = StringHelper.lastIndexOfLetter( name );
		final String suffix = Integer.toString( uniqueInteger ) + '_';

		String alias = name;
		if ( lastLetter == -1 ) {
			alias = "column";
		}
		else if ( name.length() > lastLetter + 1 ) {
			alias = name.substring( 0, lastLetter + 1 );
		}

		boolean useRawName = name.length() + suffix.length() <= dialect.getMaxAliasLength()
				&& !quoted && !name.toLowerCase( Locale.ROOT ).equals( "rowid" );
		if ( !useRawName ) {
			if ( suffix.length() >= dialect.getMaxAliasLength() ) {
				throw new MappingException(
						String.format(
								"Unique suffix [%s] length must be less than maximum [%d]",
								suffix, dialect.getMaxAliasLength()
						)
				);
			}
			if ( alias.length() + suffix.length() > dialect.getMaxAliasLength() ) {
				alias = alias.substring( 0, dialect.getMaxAliasLength() - suffix.length() );
			}
		}
		return alias + suffix;
	}

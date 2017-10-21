	protected final String selectString(List associations) throws MappingException {

		if ( associations.size() == 0 ) {
			return "";
		}
		else {
			StringBuilder buf = new StringBuilder( associations.size() * 100 );
			int entityAliasCount = 0;
			int collectionAliasCount = 0;
			for ( int i = 0; i < associations.size(); i++ ) {
				OuterJoinableAssociation join = (OuterJoinableAssociation) associations.get( i );
				OuterJoinableAssociation next = ( i == associations.size() - 1 )
						? null
						: (OuterJoinableAssociation) associations.get( i + 1 );
				final Joinable joinable = join.getJoinable();
				final String entitySuffix = ( suffixes == null || entityAliasCount >= suffixes.length )
						? null
						: suffixes[entityAliasCount];
				final String collectionSuffix = ( collectionSuffixes == null || collectionAliasCount >= collectionSuffixes.length )
						? null
						: collectionSuffixes[collectionAliasCount];
				final String selectFragment = joinable.selectFragment(
						next == null ? null : next.getJoinable(),
						next == null ? null : next.getRHSAlias(),
						join.getRHSAlias(),
						entitySuffix,
						collectionSuffix,
						join.getJoinType() == JoinType.LEFT_OUTER_JOIN
				);
				if ( selectFragment.trim().length() > 0 ) {
					buf.append( ", " ).append( selectFragment );
				}
				if ( joinable.consumesEntityAlias() ) {
					entityAliasCount++;
				}
				if ( joinable.consumesCollectionAlias() &&
						join.getJoinType() == JoinType.LEFT_OUTER_JOIN &&
						!join.hasRestriction() ) {
					collectionAliasCount++;
				}
			}
			return buf.toString();
		}
	}

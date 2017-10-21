	private void renderManyToManyJoin(
			Join join,
			JoinFragment joinFragment) {

		// for many-to-many we have 3 table aliases.  By way of example, consider a normal m-n: User<->Role
		// where User is the FetchOwner and Role (User.roles) is the Fetch.  We'd have:
		//		1) the owner's table : user - in terms of rendering the joins (not the fetch select fragments), the
		// 			lhs table alias is only needed to qualify the lhs join columns, but we already have the qualified
		// 			columns here (aliasedLhsColumnNames)
		//final String ownerTableAlias = ...;
		//		2) the m-n table : user_role
		//		3) the element table : role
		final EntityPersister entityPersister = ( (EntityQuerySpace) join.getRightHandSide() ).getEntityPersister();
		final String entityTableAlias = aliasResolutionContext.resolveSqlTableAliasFromQuerySpaceUid(
			join.getRightHandSide().getUid()
		);

		if ( StringHelper.isEmpty( entityTableAlias ) ) {
			throw new IllegalStateException( "Collection element (many-to-many) table alias cannot be empty" );
		}

		final String manyToManyFilter;
		if ( JoinDefinedByMetadata.class.isInstance( join ) &&
				CollectionPropertyNames.COLLECTION_ELEMENTS.equals( ( (JoinDefinedByMetadata) join ).getJoinedPropertyName() ) ) {
			final CollectionQuerySpace leftHandSide = (CollectionQuerySpace) join.getLeftHandSide();
			final CollectionPersister persister = leftHandSide.getCollectionPersister();
			manyToManyFilter = persister.getManyToManyFilterFragment(
					entityTableAlias,
					buildingParameters.getQueryInfluencers().getEnabledFilters()
			);
		}
		else {
			manyToManyFilter = null;
		}

		addJoins(
				join,
				joinFragment,
				(Joinable) entityPersister,
				manyToManyFilter
		);
	}

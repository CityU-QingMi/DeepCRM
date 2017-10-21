	@Override
	protected String generateTableAlias(int n, PropertyPath path, Joinable joinable) {
		// TODO: deal with side-effects (changes to includeInResultRowList, userAliasList, resultTypeList)!!!

		// for collection-of-entity, we are called twice for given "path"
		// once for the collection Joinable, once for the entity Joinable.
		// the second call will/must "consume" the alias + perform side effects according to consumesEntityAlias()
		// for collection-of-other, however, there is only one call 
		// it must "consume" the alias + perform side effects, despite what consumeEntityAlias() return says
		// 
		// note: the logic for adding to the userAliasList is still strictly based on consumesEntityAlias return value
		boolean checkForSqlAlias = joinable.consumesEntityAlias();

		if ( !checkForSqlAlias && joinable.isCollection() ) {
			// is it a collection-of-other (component or value) ?
			CollectionPersister collectionPersister = (CollectionPersister) joinable;
			Type elementType = collectionPersister.getElementType();
			if ( elementType.isComponentType() || !elementType.isEntityType() ) {
				checkForSqlAlias = true;
			}
		}

		String sqlAlias = null;

		if ( checkForSqlAlias ) {
			final Criteria subcriteria = translator.getCriteria( path.getFullPath() );
			sqlAlias = subcriteria == null ? null : translator.getSQLAlias( subcriteria );

			if ( joinable.consumesEntityAlias() && !translator.hasProjection() ) {
				includeInResultRowList.add( subcriteria != null && subcriteria.getAlias() != null );
				if ( sqlAlias != null ) {
					if ( subcriteria.getAlias() != null ) {
						userAliasList.add( subcriteria.getAlias() );
						resultTypeList.add( translator.getResultType( subcriteria ) );
					}
				}
			}
		}

		if ( sqlAlias == null ) {
			sqlAlias = super.generateTableAlias( n + translator.getSQLAliasCount(), path, joinable );
		}

		return sqlAlias;
	}

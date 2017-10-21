	public String renderMapKeyPropertySelectFragment(int size, int k) {
		if ( persister == null ) {
			throw new IllegalStateException( "Unexpected state in call to renderMapKeyPropertySelectFragment" );
		}

		final String fragment = ( (Queryable) persister ).propertySelectFragment(
				getTableAlias(),
				getSuffix( size, k ),
				false
		);
		return trimLeadingCommaAndSpaces( fragment );

//		if ( queryableCollection == null
//				|| !Map.class.isAssignableFrom( queryableCollection.getCollectionType().getReturnedClass() ) ) {
//			throw new IllegalStateException( "Illegal call to renderMapKeyPropertySelectFragment() when FromElement is not a Map" );
//		}
//
//		if ( !queryableCollection.getIndexType().isEntityType() ) {
//			return null;
//		}
//
//		final HqlSqlWalker walker = fromElement.getWalker();
//		final SessionFactoryHelper sfh = walker.getSessionFactoryHelper();
//		final SessionFactoryImplementor sf = sfh.getFactory();
//
//		final EntityType indexEntityType = (EntityType) queryableCollection.getIndexType();
//		final EntityPersister indexEntityPersister = (EntityPersister) indexEntityType.getAssociatedJoinable( sf );
//
//		final String fragment = ( (Queryable) indexEntityPersister ).propertySelectFragment(
//				getTableAlias(),
//				getSuffix( size, k ),
//				false
//		);
//		return trimLeadingCommaAndSpaces( fragment );
	}

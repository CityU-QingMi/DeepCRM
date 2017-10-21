	public static FetchStyle determineFetchStyleByMetadata(
			FetchMode mappingFetchMode,
			AssociationType type,
			SessionFactoryImplementor sessionFactory) {
		if ( !type.isEntityType() && !type.isCollectionType() ) {
			return FetchStyle.SELECT;
		}

		if ( mappingFetchMode == FetchMode.JOIN ) {
			return FetchStyle.JOIN;
		}

		if ( type.isEntityType() ) {
			EntityPersister persister = (EntityPersister) type.getAssociatedJoinable( sessionFactory );
			if ( persister.isBatchLoadable() ) {
				return FetchStyle.BATCH;
			}
			else if ( mappingFetchMode == FetchMode.SELECT ) {
				return FetchStyle.SELECT;
			}
			else if ( !persister.hasProxy() ) {
				return FetchStyle.JOIN;
			}
		}
		else {
			CollectionPersister persister = (CollectionPersister) type.getAssociatedJoinable( sessionFactory );
			if ( persister instanceof AbstractCollectionPersister
					&& ( (AbstractCollectionPersister) persister ).isSubselectLoadable() ) {
				return FetchStyle.SUBSELECT;
			}
			else if ( persister.getBatchSize() > 0 ) {
				return FetchStyle.BATCH;
			}
		}
		return FetchStyle.SELECT;
	}

	@Override
	public List<PersistentCollectionChangeData> mapCollectionChanges(
			SessionImplementor session, String referencingPropertyName,
			PersistentCollection newColl,
			Serializable oldColl, Serializable id) {
		final List<PersistentCollectionChangeData> parentCollectionChanges = parentMapper.mapCollectionChanges(
				session,
				referencingPropertyName,
				newColl,
				oldColl,
				id
		);

		final List<PersistentCollectionChangeData> mainCollectionChanges = main.mapCollectionChanges(
				session,
				referencingPropertyName,
				newColl,
				oldColl,
				id
		);

		if ( parentCollectionChanges == null ) {
			return mainCollectionChanges;
		}
		else {
			if ( mainCollectionChanges != null ) {
				parentCollectionChanges.addAll( mainCollectionChanges );
			}
			return parentCollectionChanges;
		}
	}

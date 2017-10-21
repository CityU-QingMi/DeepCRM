	@Override
	public List<PersistentCollectionChangeData> mapCollectionChanges(
			SessionImplementor session,
			String referencingPropertyName,
			PersistentCollection newColl,
			Serializable oldColl, Serializable id) {
		final Pair<PropertyMapper, String> pair = getMapperAndDelegatePropName( referencingPropertyName );
		final PropertyMapper mapper = pair.getFirst();
		if ( mapper != null ) {
			return mapper.mapCollectionChanges( session, pair.getSecond(), newColl, oldColl, id );
		}
		else {
			return null;
		}
	}

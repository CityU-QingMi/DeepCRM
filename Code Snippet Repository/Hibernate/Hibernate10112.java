	@Override
	public Map<String, Object> generateData(Object revisionData) {
		final Map<String, Object> data = new HashMap<>();
		fillDataWithId( data, revisionData );

		if ( enversService.getGlobalConfiguration().isStoreDataAtDelete() ) {
			enversService.getEntitiesConfigurations().get( getEntityName() ).getPropertyMapper().map(
					sessionImplementor,
					data,
					propertyNames,
					state,
					state
			);
		}
		else {
			enversService.getEntitiesConfigurations().get( getEntityName() ).getPropertyMapper().map(
					sessionImplementor,
					data,
					propertyNames,
					null,
					state
			);
		}

		return data;
	}

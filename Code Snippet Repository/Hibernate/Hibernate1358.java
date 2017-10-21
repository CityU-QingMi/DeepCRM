	public void doSecondPass(Map persistentClasses) throws MappingException {
		org.hibernate.mapping.FetchProfile profile = buildingContext.getMetadataCollector().getFetchProfile(
				fetchProfileName
		);
		if ( profile != null ) {
			if ( profile.getSource() != MetadataSource.ANNOTATIONS ) {
				return;
			}
		}
		else {
			profile = new org.hibernate.mapping.FetchProfile( fetchProfileName, MetadataSource.ANNOTATIONS );
			buildingContext.getMetadataCollector().addFetchProfile( profile );
		}

		PersistentClass clazz = buildingContext.getMetadataCollector().getEntityBinding( fetch.entity().getName() );
		// throws MappingException in case the property does not exist
		clazz.getProperty( fetch.association() );

		profile.addFetch(
				fetch.entity().getName(), fetch.association(), fetch.mode().toString().toLowerCase(Locale.ROOT)
		);
	}

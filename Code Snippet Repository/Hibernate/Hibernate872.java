	public static void processFetchProfile(
			HbmLocalMetadataBuildingContext context,
			JaxbHbmFetchProfileType fetchProfileBinding,
			String containingEntityName) {
		FetchProfile profile = context.getMetadataCollector().getFetchProfile( fetchProfileBinding.getName() );
		if ( profile == null ) {
			log.debugf( "Creating FetchProfile : %s", fetchProfileBinding.getName() );
			profile = new FetchProfile( fetchProfileBinding.getName(), MetadataSource.HBM );
			context.getMetadataCollector().addFetchProfile( profile );
		}

		for ( JaxbHbmFetchProfileType.JaxbHbmFetch fetchBinding : fetchProfileBinding.getFetch() ) {
			String entityName = fetchBinding.getEntity();
			if ( entityName == null ) {
				entityName = containingEntityName;
			}
			if ( entityName == null ) {
				throw new org.hibernate.boot.MappingException(
						String.format(
								"Unable to determine entity for fetch-profile fetch [%s:%s]",
								profile.getName(),
								fetchBinding.getAssociation()
						),
						context.getOrigin()
				);
			}
			profile.addFetch( entityName, fetchBinding.getAssociation(), fetchBinding.getStyle().value() );
		}
	}

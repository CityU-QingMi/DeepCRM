		private ArrayList<MetadataSourceType> resolveInitialSourceProcessOrdering(ConfigurationService configService) {
			final ArrayList<MetadataSourceType> initialSelections = new ArrayList<MetadataSourceType>();

			final String sourceProcessOrderingSetting = configService.getSetting(
					AvailableSettings.ARTIFACT_PROCESSING_ORDER,
					StandardConverters.STRING
			);
			if ( sourceProcessOrderingSetting != null ) {
				final String[] orderChoices = StringHelper.split( ",; ", sourceProcessOrderingSetting, false );
				initialSelections.addAll( CollectionHelper.<MetadataSourceType>arrayList( orderChoices.length ) );
				for ( String orderChoice : orderChoices ) {
					initialSelections.add( MetadataSourceType.parsePrecedence( orderChoice ) );
				}
			}
			if ( initialSelections.isEmpty() ) {
				initialSelections.add( MetadataSourceType.HBM );
				initialSelections.add( MetadataSourceType.CLASS );
			}

			return initialSelections;
		}

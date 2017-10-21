	private void applyCaching(MappingDocument mappingDocument, Caching caching, Collection collection) {
		if ( caching == null || caching.getRequested() == TruthValue.UNKNOWN ) {
			// see if JPA's SharedCacheMode indicates we should implicitly apply caching
			switch ( mappingDocument.getBuildingOptions().getSharedCacheMode() ) {
				case ALL: {
					caching = new Caching(
							null,
							mappingDocument.getBuildingOptions().getImplicitCacheAccessType(),
							false,
							TruthValue.UNKNOWN
					);
					break;
				}
				case NONE: {
					// Ideally we'd disable all caching...
					break;
				}
				case ENABLE_SELECTIVE: {
					// this is default behavior for hbm.xml
					break;
				}
				case DISABLE_SELECTIVE: {
					// really makes no sense for hbm.xml
					break;
				}
				default: {
					// null or UNSPECIFIED, nothing to do.  IMO for hbm.xml this is equivalent
					// to ENABLE_SELECTIVE
					break;
				}
			}
		}

		if ( caching == null || caching.getRequested() == TruthValue.FALSE ) {
			return;
		}

		if ( caching.getAccessType() != null ) {
			collection.setCacheConcurrencyStrategy( caching.getAccessType().getExternalName() );
		}
		else {
			collection.setCacheConcurrencyStrategy( mappingDocument.getBuildingOptions().getImplicitCacheAccessType().getExternalName() );
		}
		collection.setCacheRegionName( caching.getRegion() );
//		collection.setCachingExplicitlyRequested( caching.getRequested() != TruthValue.UNKNOWN );
	}

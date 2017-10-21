	@Override
	public void cacheManagerStarting(GlobalComponentRegistry gcr, GlobalConfiguration globalCfg) {
		Map<Integer, AdvancedExternalizer<?>> externalizerMap = globalCfg.serialization().advancedExternalizers();
		externalizerMap.put( Externalizers.UUID, new Externalizers.UUIDExternalizer() );
		externalizerMap.put( Externalizers.TOMBSTONE, new Tombstone.Externalizer() );
		externalizerMap.put( Externalizers.EXCLUDE_TOMBSTONES_FILTER, new Tombstone.ExcludeTombstonesFilterExternalizer() );
		externalizerMap.put( Externalizers.TOMBSTONE_UPDATE, new TombstoneUpdate.Externalizer() );
		externalizerMap.put( Externalizers.FUTURE_UPDATE, new FutureUpdate.Externalizer() );
		externalizerMap.put( Externalizers.VERSIONED_ENTRY, new VersionedEntry.Externalizer() );
		externalizerMap.put( Externalizers.EXCLUDE_EMPTY_EXTRACT_VALUE, new VersionedEntry.ExcludeEmptyExtractValueExternalizer() );
	}

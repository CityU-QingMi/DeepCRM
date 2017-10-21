		public boolean cache(Serializable pk, Object[] naturalIdValues) {
			if ( pk == null ) {
				return false;
			}
			final CachedNaturalId initial = pkToNaturalIdMap.get( pk );
			if ( initial != null ) {
				if ( initial.isSame( naturalIdValues ) ) {
					return false;
				}
				naturalIdToPkMap.remove( initial );
			}

			final CachedNaturalId cachedNaturalId = new CachedNaturalId( persister, naturalIdValues );
			pkToNaturalIdMap.put( pk, cachedNaturalId );
			naturalIdToPkMap.put( cachedNaturalId, pk );
			
			return true;
		}

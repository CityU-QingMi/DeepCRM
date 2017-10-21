		public boolean sameAsCached(Serializable pk, Object[] naturalIdValues) {
			if ( pk == null ) {
				return false;
			}
			final CachedNaturalId initial = pkToNaturalIdMap.get( pk );
			if ( initial != null ) {
				if ( initial.isSame( naturalIdValues ) ) {
					return true;
				}
			}
			return false;
		}

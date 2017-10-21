		public HQLQueryPlanKey(String query, boolean shallow, Map enabledFilters) {
			this.query = query;
			this.shallow = shallow;
			if ( CollectionHelper.isEmpty( enabledFilters ) ) {
				filterKeys = Collections.emptySet();
			}
			else {
				final Set<DynamicFilterKey> tmp = new HashSet<DynamicFilterKey>(
						CollectionHelper.determineProperSizing( enabledFilters ),
						CollectionHelper.LOAD_FACTOR
				);
				for ( Object o : enabledFilters.values() ) {
					tmp.add( new DynamicFilterKey( (FilterImpl) o ) );
				}
				this.filterKeys = Collections.unmodifiableSet( tmp );
			}

			int hash = query.hashCode();
			hash = 29 * hash + ( shallow ? 1 : 0 );
			hash = 29 * hash + filterKeys.hashCode();
			this.hashCode = hash;
		}

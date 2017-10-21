		private DynamicFilterKey(FilterImpl filter) {
			this.filterName = filter.getName();
			if ( filter.getParameters().isEmpty() ) {
				parameterMetadata = Collections.emptyMap();
			}
			else {
				parameterMetadata = new HashMap<String,Integer>(
						CollectionHelper.determineProperSizing( filter.getParameters() ),
						CollectionHelper.LOAD_FACTOR
				);
				for ( Object o : filter.getParameters().entrySet() ) {
					final Map.Entry entry = (Map.Entry) o;
					final String key = (String) entry.getKey();
					final Integer valueCount;
					if ( Collection.class.isInstance( entry.getValue() ) ) {
						valueCount = ( (Collection) entry.getValue() ).size();
					}
					else {
						valueCount = 1;
					}
					parameterMetadata.put( key, valueCount );
				}
			}

			int hash = filterName.hashCode();
			hash = 31 * hash + parameterMetadata.hashCode();
			this.hashCode = hash;
		}

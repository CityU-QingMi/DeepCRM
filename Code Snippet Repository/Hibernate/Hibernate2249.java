		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			final DynamicFilterKey that = (DynamicFilterKey) o;
			return filterName.equals( that.filterName )
					&& parameterMetadata.equals( that.parameterMetadata );

		}

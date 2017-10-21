		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			final ParameterMetadataKey that = (ParameterMetadataKey) o;

			return isOrdinalParameterZeroBased == that.isOrdinalParameterZeroBased
					&& query.equals( that.query );

		}

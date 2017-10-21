		@Override
		public String[] sqlCreateStrings(Dialect dialect) {
			final String[] createStrings = dialect.getCreateSequenceStrings(
					getName(),
					getInitialValue(),
					getSourceIncrementSize()
			);

			if ( dialect instanceof Oracle8iDialect ) {
				for ( int i = 0; i < createStrings.length; ++i ) {
					createStrings[ i ] = createStrings[ i ] + ORDER;
				}
			}

			return createStrings;
		}

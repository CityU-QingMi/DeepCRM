		@Override
		public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source) {
			// This impl uses the owner entity table name instead of the JPA entity name when
			// generating the implicit name.
			final String name = source.getOwningPhysicalTableName().getText()
					+ '_'
					+ transformAttributePath( source.getOwningAttributePath() );

			return toIdentifier( name, source.getBuildingContext() );
		}

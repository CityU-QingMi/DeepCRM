		@Override
		public Type findType(String attributeName) {
			int i = 0;
			for ( String componentAttributeName : compositeType.getPropertyNames() ) {
				if ( attributeName.equals( componentAttributeName ) ) {
					return compositeType.getSubtypes()[i];
				}
				i++;
			}
			throw new IllegalArgumentException( "Could not find given attribute name [%s] on composite-type" );
		}

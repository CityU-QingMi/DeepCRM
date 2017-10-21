	private static void applyColumnDefault(Ejb3Column column, PropertyData inferredData) {
		final XProperty xProperty = inferredData.getProperty();
		if ( xProperty != null ) {
			ColumnDefault columnDefaultAnn = xProperty.getAnnotation( ColumnDefault.class );
			if ( columnDefaultAnn != null ) {
				column.setDefaultValue( columnDefaultAnn.value() );
			}
		}
		else {
			LOG.trace(
					"Could not perform @ColumnDefault lookup as 'PropertyData' did not give access to XProperty"
			);
		}
	}

	private void extractDataFromPropertyData(PropertyData inferredData) {
		if ( inferredData != null ) {
			XProperty property = inferredData.getProperty();
			if ( property != null ) {
				processExpression( property.getAnnotation( ColumnTransformer.class ) );
				ColumnTransformers annotations = property.getAnnotation( ColumnTransformers.class );
				if (annotations != null) {
					for ( ColumnTransformer annotation : annotations.value() ) {
						processExpression( annotation );
					}
				}
			}
		}
	}

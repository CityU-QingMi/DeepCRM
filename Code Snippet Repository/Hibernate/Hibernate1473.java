	private SqlResultSetMappings getSqlResultSetMappings(Element tree, XMLContext.Default defaults) {
		List<SqlResultSetMapping> results = buildSqlResultsetMappings( tree, defaults, classLoaderAccess );
		if ( defaults.canUseJavaAnnotations() ) {
			SqlResultSetMapping annotation = getPhysicalAnnotation( SqlResultSetMapping.class );
			addSqlResultsetMappingIfNeeded( annotation, results );
			SqlResultSetMappings annotations = getPhysicalAnnotation( SqlResultSetMappings.class );
			if ( annotations != null ) {
				for ( SqlResultSetMapping current : annotations.value() ) {
					addSqlResultsetMappingIfNeeded( current, results );
				}
			}
		}
		if ( results.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( SqlResultSetMappings.class );
			ad.setValue( "value", results.toArray( new SqlResultSetMapping[results.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}

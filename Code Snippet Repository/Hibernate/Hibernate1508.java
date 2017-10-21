	private void overridesDefaultInSecondaryTable(
			SecondaryTable secTableAnn, XMLContext.Default defaults, List<SecondaryTable> secondaryTables
	) {
		if ( secTableAnn != null ) {
			//handle default values
			if ( StringHelper.isNotEmpty( defaults.getCatalog() )
					|| StringHelper.isNotEmpty( defaults.getSchema() ) ) {
				AnnotationDescriptor annotation = new AnnotationDescriptor( SecondaryTable.class );
				annotation.setValue( "name", secTableAnn.name() );
				annotation.setValue( "schema", secTableAnn.schema() );
				annotation.setValue( "catalog", secTableAnn.catalog() );
				annotation.setValue( "uniqueConstraints", secTableAnn.uniqueConstraints() );
				annotation.setValue( "pkJoinColumns", secTableAnn.pkJoinColumns() );
				if ( StringHelper.isEmpty( (String) annotation.valueOf( "schema" ) )
						&& StringHelper.isNotEmpty( defaults.getSchema() ) ) {
					annotation.setValue( "schema", defaults.getSchema() );
				}
				if ( StringHelper.isEmpty( (String) annotation.valueOf( "catalog" ) )
						&& StringHelper.isNotEmpty( defaults.getCatalog() ) ) {
					annotation.setValue( "catalog", defaults.getCatalog() );
				}
				secondaryTables.add( (SecondaryTable) AnnotationFactory.create( annotation ) );
			}
			else {
				secondaryTables.add( secTableAnn );
			}
		}
	}

	private TableGenerator getTableGenerator(Element tree, XMLContext.Default defaults) {
		Element element = tree != null ? tree.element( annotationToXml.get( TableGenerator.class ) ) : null;
		if ( element != null ) {
			return buildTableGeneratorAnnotation( element, defaults );
		}
		else if ( defaults.canUseJavaAnnotations() && isPhysicalAnnotationPresent( TableGenerator.class ) ) {
			TableGenerator tableAnn = getPhysicalAnnotation( TableGenerator.class );
			if ( StringHelper.isNotEmpty( defaults.getSchema() )
					|| StringHelper.isNotEmpty( defaults.getCatalog() ) ) {
				AnnotationDescriptor annotation = new AnnotationDescriptor( TableGenerator.class );
				annotation.setValue( "name", tableAnn.name() );
				annotation.setValue( "table", tableAnn.table() );
				annotation.setValue( "catalog", tableAnn.table() );
				if ( StringHelper.isEmpty( (String) annotation.valueOf( "catalog" ) )
						&& StringHelper.isNotEmpty( defaults.getCatalog() ) ) {
					annotation.setValue( "catalog", defaults.getCatalog() );
				}
				annotation.setValue( "schema", tableAnn.table() );
				if ( StringHelper.isEmpty( (String) annotation.valueOf( "schema" ) )
						&& StringHelper.isNotEmpty( defaults.getSchema() ) ) {
					annotation.setValue( "catalog", defaults.getSchema() );
				}
				annotation.setValue( "pkColumnName", tableAnn.pkColumnName() );
				annotation.setValue( "valueColumnName", tableAnn.valueColumnName() );
				annotation.setValue( "pkColumnValue", tableAnn.pkColumnValue() );
				annotation.setValue( "initialValue", tableAnn.initialValue() );
				annotation.setValue( "allocationSize", tableAnn.allocationSize() );
				annotation.setValue( "uniqueConstraints", tableAnn.uniqueConstraints() );
				return AnnotationFactory.create( annotation );
			}
			else {
				return tableAnn;
			}
		}
		else {
			return null;
		}
	}

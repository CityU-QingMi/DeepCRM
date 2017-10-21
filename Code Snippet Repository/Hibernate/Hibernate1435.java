	private JoinTable overridesDefaultsInJoinTable(Annotation annotation, XMLContext.Default defaults) {
		//no element but might have some default or some annotation
		boolean defaultToJoinTable = !( isPhysicalAnnotationPresent( JoinColumn.class )
				|| isPhysicalAnnotationPresent( JoinColumns.class ) );
		final Class<? extends Annotation> annotationClass = annotation.annotationType();
		defaultToJoinTable = defaultToJoinTable &&
				( ( annotationClass == ManyToMany.class && StringHelper.isEmpty( ( (ManyToMany) annotation ).mappedBy() ) )
						|| ( annotationClass == OneToMany.class && StringHelper.isEmpty( ( (OneToMany) annotation ).mappedBy() ) )
						|| ( annotationClass == ElementCollection.class )
				);
		final Class<JoinTable> annotationType = JoinTable.class;
		if ( defaultToJoinTable
				&& ( StringHelper.isNotEmpty( defaults.getCatalog() )
				|| StringHelper.isNotEmpty( defaults.getSchema() ) ) ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( annotationType );
			if ( defaults.canUseJavaAnnotations() ) {
				JoinTable table = getPhysicalAnnotation( annotationType );
				if ( table != null ) {
					ad.setValue( "name", table.name() );
					ad.setValue( "schema", table.schema() );
					ad.setValue( "catalog", table.catalog() );
					ad.setValue( "uniqueConstraints", table.uniqueConstraints() );
					ad.setValue( "joinColumns", table.joinColumns() );
					ad.setValue( "inverseJoinColumns", table.inverseJoinColumns() );
				}
			}
			if ( StringHelper.isEmpty( (String) ad.valueOf( "schema" ) )
					&& StringHelper.isNotEmpty( defaults.getSchema() ) ) {
				ad.setValue( "schema", defaults.getSchema() );
			}
			if ( StringHelper.isEmpty( (String) ad.valueOf( "catalog" ) )
					&& StringHelper.isNotEmpty( defaults.getCatalog() ) ) {
				ad.setValue( "catalog", defaults.getCatalog() );
			}
			return AnnotationFactory.create( ad );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( annotationType );
		}
		else {
			return null;
		}
	}

	private AssociationOverrides getAssociationOverrides(Element tree, XMLContext.Default defaults, boolean mergeWithAnnotations) {
		List<AssociationOverride> attributes = buildAssociationOverrides( tree, defaults );
		if ( mergeWithAnnotations && defaults.canUseJavaAnnotations() ) {
			AssociationOverride annotation = getPhysicalAnnotation( AssociationOverride.class );
			addAssociationOverrideIfNeeded( annotation, attributes );
			AssociationOverrides annotations = getPhysicalAnnotation( AssociationOverrides.class );
			if ( annotations != null ) {
				for ( AssociationOverride current : annotations.value() ) {
					addAssociationOverrideIfNeeded( current, attributes );
				}
			}
		}
		if ( attributes.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( AssociationOverrides.class );
			ad.setValue( "value", attributes.toArray( new AssociationOverride[attributes.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}

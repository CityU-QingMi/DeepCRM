	private AttributeOverrides mergeAttributeOverrides(XMLContext.Default defaults, List<AttributeOverride> attributes, boolean mergeWithAnnotations) {
		if ( mergeWithAnnotations && defaults.canUseJavaAnnotations() ) {
			AttributeOverride annotation = getPhysicalAnnotation( AttributeOverride.class );
			addAttributeOverrideIfNeeded( annotation, attributes );
			AttributeOverrides annotations = getPhysicalAnnotation( AttributeOverrides.class );
			if ( annotations != null ) {
				for ( AttributeOverride current : annotations.value() ) {
					addAttributeOverrideIfNeeded( current, attributes );
				}
			}
		}
		if ( attributes.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( AttributeOverrides.class );
			ad.setValue( "value", attributes.toArray( new AttributeOverride[attributes.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}

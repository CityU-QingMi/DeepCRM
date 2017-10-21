	private IdClass getIdClass(Element tree, XMLContext.Default defaults) {
		Element element = tree == null ? null : tree.element( "id-class" );
		if ( element != null ) {
			Attribute attr = element.attribute( "class" );
			if ( attr != null ) {
				AnnotationDescriptor ad = new AnnotationDescriptor( IdClass.class );
				Class clazz;
				try {
					clazz = classLoaderAccess.classForName(
							XMLContext.buildSafeClassName( attr.getValue(), defaults )
					);
				}
				catch ( ClassLoadingException e ) {
					throw new AnnotationException( "Unable to find id-class: " + attr.getValue(), e );
				}
				ad.setValue( "value", clazz );
				return AnnotationFactory.create( ad );
			}
			else {
				throw new AnnotationException( "id-class without class. " + SCHEMA_VALIDATION );
			}
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( IdClass.class );
		}
		else {
			return null;
		}
	}

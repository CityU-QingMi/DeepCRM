	private EntityListeners getEntityListeners(Element tree, XMLContext.Default defaults) {
		Element element = tree != null ? tree.element( "entity-listeners" ) : null;
		if ( element != null ) {
			List<Class> entityListenerClasses = new ArrayList<Class>();
			for ( Element subelement : (List<Element>) element.elements( "entity-listener" ) ) {
				String className = subelement.attributeValue( "class" );
				try {
					entityListenerClasses.add(
							classLoaderAccess.classForName(
									XMLContext.buildSafeClassName( className, defaults )
							)
					);
				}
				catch ( ClassLoadingException e ) {
					throw new AnnotationException(
							"Unable to find " + element.getPath() + ".class: " + className, e
					);
				}
			}
			AnnotationDescriptor ad = new AnnotationDescriptor( EntityListeners.class );
			ad.setValue( "value", entityListenerClasses.toArray( new Class[entityListenerClasses.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( EntityListeners.class );
		}
		else {
			return null;
		}
	}

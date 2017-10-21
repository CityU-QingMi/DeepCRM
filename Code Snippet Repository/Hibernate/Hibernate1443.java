	private void getMapKeyClass(List<Annotation> annotationList, Element element, XMLContext.Default defaults) {
		String nodeName = "map-key-class";
		Element subelement = element != null ? element.element( nodeName ) : null;
		if ( subelement != null ) {
			String mapKeyClassName = subelement.attributeValue( "class" );
			AnnotationDescriptor ad = new AnnotationDescriptor( MapKeyClass.class );
			if ( StringHelper.isNotEmpty( mapKeyClassName ) ) {
				Class clazz;
				try {
					clazz = classLoaderAccess.classForName(
							XMLContext.buildSafeClassName( mapKeyClassName, defaults )
					);
				}
				catch ( ClassLoadingException e ) {
					throw new AnnotationException(
							"Unable to find " + element.getPath() + " " + nodeName + ": " + mapKeyClassName, e
					);
				}
				ad.setValue( "value", clazz );
			}
			annotationList.add( AnnotationFactory.create( ad ) );
		}
	}

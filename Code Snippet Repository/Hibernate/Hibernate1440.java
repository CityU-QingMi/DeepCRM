	private void addTargetClass(Element element, AnnotationDescriptor ad, String nodeName, XMLContext.Default defaults) {
		String className = element.attributeValue( nodeName );
		if ( className != null ) {
			Class clazz;
			try {
				clazz = classLoaderAccess.classForName( XMLContext.buildSafeClassName( className, defaults ) );
			}
			catch ( ClassLoadingException e ) {
				throw new AnnotationException(
						"Unable to find " + element.getPath() + " " + nodeName + ": " + className, e
				);
			}
			ad.setValue( getJavaAttributeNameFromXMLOne( nodeName ), clazz );
		}
	}

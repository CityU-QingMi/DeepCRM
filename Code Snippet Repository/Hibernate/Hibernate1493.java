	public static List buildNamedQueries(
			Element element,
			boolean isNative,
			XMLContext.Default defaults,
			ClassLoaderAccess classLoaderAccess) {
		if ( element == null ) {
			return new ArrayList();
		}
		List namedQueryElementList = isNative ?
				element.elements( "named-native-query" ) :
				element.elements( "named-query" );
		List namedQueries = new ArrayList();
		for ( Object aNamedQueryElementList : namedQueryElementList ) {
			Element subelement = (Element) aNamedQueryElementList;
			AnnotationDescriptor ann = new AnnotationDescriptor(
					isNative ? NamedNativeQuery.class : NamedQuery.class
			);
			copyStringAttribute( ann, subelement, "name", false );
			Element queryElt = subelement.element( "query" );
			if ( queryElt == null ) {
				throw new AnnotationException( "No <query> element found." + SCHEMA_VALIDATION );
			}
			copyStringElement( queryElt, ann, "query" );
			List<Element> elements = subelement.elements( "hint" );
			buildQueryHints( elements, ann );
			String clazzName = subelement.attributeValue( "result-class" );
			if ( StringHelper.isNotEmpty( clazzName ) ) {
				Class clazz;
				try {
					clazz = classLoaderAccess.classForName(
							XMLContext.buildSafeClassName( clazzName, defaults )
					);
				}
				catch (ClassLoadingException e) {
					throw new AnnotationException( "Unable to find entity-class: " + clazzName, e );
				}
				ann.setValue( "resultClass", clazz );
			}
			copyStringAttribute( ann, subelement, "result-set-mapping", false );
			namedQueries.add( AnnotationFactory.create( ann ) );
		}
		return namedQueries;
	}

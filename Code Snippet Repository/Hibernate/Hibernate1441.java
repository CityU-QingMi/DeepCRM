	private void getElementCollection(List<Annotation> annotationList, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "element-collection".equals( element.getName() ) ) {
				AnnotationDescriptor ad = new AnnotationDescriptor( ElementCollection.class );
				addTargetClass( element, ad, "target-class", defaults );
				getFetchType( ad, element );
				getOrderBy( annotationList, element );
				getOrderColumn( annotationList, element );
				getMapKey( annotationList, element );
				getMapKeyClass( annotationList, element, defaults );
				getMapKeyTemporal( annotationList, element );
				getMapKeyEnumerated( annotationList, element );
				getMapKeyColumn( annotationList, element );
				buildMapKeyJoinColumns( annotationList, element );
				Annotation annotation = getColumn( element.element( "column" ), false, element );
				addIfNotNull( annotationList, annotation );
				getTemporal( annotationList, element );
				getEnumerated( annotationList, element );
				getLob( annotationList, element );
				//Both map-key-attribute-overrides and attribute-overrides
				//translate into AttributeOverride annotations, which need
				//need to be wrapped in the same AttributeOverrides annotation.
				List<AttributeOverride> attributes = new ArrayList<AttributeOverride>();
				attributes.addAll( buildAttributeOverrides( element, "map-key-attribute-override" ) );
				attributes.addAll( buildAttributeOverrides( element, "attribute-override" ) );
				annotation = mergeAttributeOverrides( defaults, attributes, false );
				addIfNotNull( annotationList, annotation );
				annotation = getAssociationOverrides( element, defaults, false );
				addIfNotNull( annotationList, annotation );
				getCollectionTable( annotationList, element, defaults );
				annotationList.add( AnnotationFactory.create( ad ) );
				getAccessType( annotationList, element );
			}
		}
	}

	public static List<NamedEntityGraph> buildNamedEntityGraph(
			Element element,
			XMLContext.Default defaults,
			ClassLoaderAccess classLoaderAccess) {
		if ( element == null ) {
			return new ArrayList<NamedEntityGraph>();
		}
		List<NamedEntityGraph> namedEntityGraphList = new ArrayList<NamedEntityGraph>();
		List<Element> namedEntityGraphElements = element.elements( "named-entity-graph" );
		for ( Element subElement : namedEntityGraphElements ) {
			AnnotationDescriptor ann = new AnnotationDescriptor( NamedEntityGraph.class );
			copyStringAttribute( ann, subElement, "name", false );
			copyBooleanAttribute( ann, subElement, "include-all-attributes" );
			bindNamedAttributeNodes( subElement, ann );

			List<Element> subgraphNodes = subElement.elements( "subgraph" );
			List<Element> subclassSubgraphNodes = subElement.elements( "subclass-subgraph" );
			if(!subclassSubgraphNodes.isEmpty()) {
				subgraphNodes.addAll( subclassSubgraphNodes );
			}
			bindNamedSubgraph( defaults, ann, subgraphNodes, classLoaderAccess );
			namedEntityGraphList.add( (NamedEntityGraph) AnnotationFactory.create( ann ) );
		}
		//TODO
		return namedEntityGraphList;
	}

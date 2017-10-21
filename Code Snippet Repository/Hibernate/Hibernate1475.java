	private static void bindNamedSubgraph(
			XMLContext.Default defaults,
			AnnotationDescriptor ann,
			List<Element> subgraphNodes,
			ClassLoaderAccess classLoaderAccess) {
		List<NamedSubgraph> annSubgraphNodes = new ArrayList<NamedSubgraph>(  );
		for(Element subgraphNode : subgraphNodes){
			AnnotationDescriptor annSubgraphNode = new AnnotationDescriptor( NamedSubgraph.class );
			copyStringAttribute( annSubgraphNode, subgraphNode, "name", true );
			String clazzName = subgraphNode.attributeValue( "class" );
			Class clazz;
			try {
				clazz = classLoaderAccess.classForName(
						XMLContext.buildSafeClassName( clazzName, defaults )
				);
			}
			catch ( ClassLoadingException e ) {
				throw new AnnotationException( "Unable to find entity-class: " + clazzName, e );
			}
			annSubgraphNode.setValue( "type", clazz );
			bindNamedAttributeNodes(subgraphNode, annSubgraphNode);
			annSubgraphNodes.add( (NamedSubgraph) AnnotationFactory.create( annSubgraphNode ) );
		}

		ann.setValue( "subgraphs", annSubgraphNodes.toArray( new NamedSubgraph[annSubgraphNodes.size()] ) );
	}

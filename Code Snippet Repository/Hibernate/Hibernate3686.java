	@Override
	public void startingCollectionElements(
			final CollectionElementDefinition elementDefinition) {
		AttributeNodeImplementor attributeNode = attributeStack.peekLast();
		GraphNodeImplementor subGraphNode = NON_EXIST_SUBGRAPH_NODE;
		Map<Class, Subgraph> subGraphs = attributeNode.getSubgraphs();
		Class javaType = elementDefinition.getType().getReturnedClass();
		if ( !subGraphs.isEmpty() && subGraphs.containsKey( javaType ) ) {
			subGraphNode = (GraphNodeImplementor) subGraphs.get( javaType );
		}
		graphStack.addLast( subGraphNode );
		super.startingCollectionElements( elementDefinition );
	}

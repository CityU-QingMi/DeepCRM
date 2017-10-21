	@Override
	public void startingCollectionIndex(final CollectionIndexDefinition indexDefinition) {
		AttributeNodeImplementor attributeNode = attributeStack.peekLast();
		GraphNodeImplementor subGraphNode = NON_EXIST_SUBGRAPH_NODE;
		Map<Class, Subgraph> subGraphs = attributeNode.getKeySubgraphs();
		Class javaType = indexDefinition.getType().getReturnedClass();
		if ( !subGraphs.isEmpty() && subGraphs.containsKey( javaType ) ) {
			subGraphNode = (GraphNodeImplementor) subGraphs.get( javaType );
		}
		graphStack.addLast( subGraphNode );
		super.startingCollectionIndex( indexDefinition );
	}

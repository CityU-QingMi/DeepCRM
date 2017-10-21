	private AttributeNodeImpl(
			SessionFactoryImplementor sessionFactory,
			ManagedType managedType,
			Attribute<?, T> attribute,
			Map<Class, Subgraph> subgraphMap,
			Map<Class, Subgraph> keySubgraphMap) {
		this.sessionFactory = sessionFactory;
		this.managedType = managedType;
		this.attribute = attribute;
		this.subgraphMap = subgraphMap;
		this.keySubgraphMap = keySubgraphMap;
	}

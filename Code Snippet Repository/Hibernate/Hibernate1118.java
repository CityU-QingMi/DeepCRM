	private LazyAttributeDescriptor(
			int attributeIndex,
			int lazyIndex,
			String name,
			Type type,
			String fetchGroupName) {
		assert attributeIndex >= lazyIndex;
		this.attributeIndex = attributeIndex;
		this.lazyIndex  = lazyIndex;
		this.name = name;
		this.type = type;
		this.fetchGroupName = fetchGroupName;
	}

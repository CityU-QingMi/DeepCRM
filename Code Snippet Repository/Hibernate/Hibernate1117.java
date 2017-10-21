	public static LazyAttributeDescriptor from(
			Property property,
			int attributeIndex,
			int lazyIndex) {
		String fetchGroupName = property.getLazyGroup();
		if ( fetchGroupName == null ) {
			fetchGroupName = property.getType().isCollectionType()
					? property.getName()
					: "DEFAULT";
		}

		return new LazyAttributeDescriptor(
				attributeIndex,
				lazyIndex,
				property.getName(),
				property.getType(),
				fetchGroupName
		);
	}

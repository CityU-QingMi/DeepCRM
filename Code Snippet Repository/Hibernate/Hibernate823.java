	protected List<AttributeSource> buildAttributeSources() {
		final List<AttributeSource> attributeSources = new ArrayList<AttributeSource>();

		AttributesHelper.Callback attributeBuildingCallback = new AttributesHelper.Callback() {
			@Override
			public AttributeSourceContainer getAttributeSourceContainer() {
				return AbstractEntitySourceImpl.this;
			}

			@Override
			public void addAttributeSource(AttributeSource attributeSource) {
				attributeSources.add( attributeSource );
			}
		};
		buildAttributeSources( attributeBuildingCallback );

		return attributeSources;
	}

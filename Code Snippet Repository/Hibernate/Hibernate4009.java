	private <X> AttributeContext<X> wrap(final AbstractManagedType<X> ownerType, final Property property) {
		return new AttributeContext<X>() {
			public AbstractManagedType<X> getOwnerType() {
				return ownerType;
			}

			public Property getPropertyMapping() {
				return property;
			}
		};
	}

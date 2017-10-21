	private static List<DiscriminatorMapping> interpretDiscriminatorMappings(AnyType anyType) {
		final Type discriminatorType = anyType.getDiscriminatorType();
		if ( ! MetaType.class.isInstance( discriminatorType ) ) {
			return Collections.emptyList();
		}

		final MetaType metaType = (MetaType) discriminatorType;
		final List<DiscriminatorMapping> discriminatorMappings = new ArrayList<DiscriminatorMapping>();
		for ( final Map.Entry<Object,String> entry : metaType.getDiscriminatorValuesToEntityNameMap().entrySet() ) {
			discriminatorMappings.add(
					new DiscriminatorMapping() {
						private final Object discriminatorValue = entry.getKey();
						private final String entityName = entry.getValue();

						@Override
						public Object getDiscriminatorValue() {
							return discriminatorValue;
						}

						@Override
						public String getEntityName() {
							return entityName;
						}
					}
			);
		}
		return discriminatorMappings;
	}

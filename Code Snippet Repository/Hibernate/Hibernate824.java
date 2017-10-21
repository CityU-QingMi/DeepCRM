	private Map<String,SecondaryTableSource> buildSecondaryTableMap() {
		if ( !SecondaryTableContainer.class.isInstance( jaxbEntityMapping ) ) {
			return Collections.emptyMap();
		}

		final HashMap<String,SecondaryTableSource> secondaryTableSourcesMap =
				new HashMap<String, SecondaryTableSource>();

		for ( final JaxbHbmSecondaryTableType joinElement :  ( (SecondaryTableContainer) jaxbEntityMapping ).getJoin() ) {
			final SecondaryTableSourceImpl secondaryTableSource = new SecondaryTableSourceImpl(
					sourceMappingDocument(),
					joinElement,
					getEntityNamingSource(),
					this
			);

			final String logicalTableName = secondaryTableSource.getLogicalTableNameForContainedColumns();
			secondaryTableSourcesMap.put( logicalTableName, secondaryTableSource );

			AttributesHelper.processAttributes(
					sourceMappingDocument(),
					new AttributesHelper.Callback() {
						@Override
						public AttributeSourceContainer getAttributeSourceContainer() {
							return AbstractEntitySourceImpl.this;
						}

						@Override
						public void addAttributeSource(AttributeSource attributeSource) {
							attributeSources.add( attributeSource );
						}
					},
					joinElement.getAttributes(),
					logicalTableName,
					NaturalIdMutability.NOT_NATURAL_ID
			);
		}
		return secondaryTableSourcesMap;
	}

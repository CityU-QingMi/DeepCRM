		protected SingularAttributeSourceAggregatedCompositeIdentifierImpl(
				MappingDocument mappingDocument,
				EmbeddedAttributeMappingAdapterAggregatedCompositeId compositeIdAdapter) {
			super(
					mappingDocument,
					compositeIdAdapter,
					new EmbeddableSourceImpl(
							mappingDocument,
							compositeIdAdapter,
							compositeIdAdapter,
							compositeIdAdapter.getAttributes(),
							false,
							false,
							null,
							NaturalIdMutability.NOT_NATURAL_ID
					),
					NaturalIdMutability.NOT_NATURAL_ID
			);
			this.compositeIdAdapter = compositeIdAdapter;
		}

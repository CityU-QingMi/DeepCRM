		private EmbeddedAttributeMappingAdapterAggregatedCompositeId(
				RootEntitySourceImpl rootEntitySource) {
			this.rootEntitySource = rootEntitySource;
			this.jaxbCompositeIdMapping = rootEntitySource.jaxbEntityMapping().getCompositeId();

			this.idAttributeRole = rootEntitySource.getAttributeRoleBase().append( jaxbCompositeIdMapping.getName() );
			this.idAttributePath = rootEntitySource.getAttributePathBase().append( jaxbCompositeIdMapping.getName() );

			this.toolingHintContext = Helper.collectToolingHints(
					rootEntitySource.getToolingHintContext(),
					jaxbCompositeIdMapping
			);
		}

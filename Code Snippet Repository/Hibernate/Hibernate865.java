	private static IdentifierSource interpretIdentifierSource(RootEntitySourceImpl rootEntitySource) {
		if ( rootEntitySource.jaxbEntityMapping().getId() == null
				&& rootEntitySource.jaxbEntityMapping().getCompositeId() == null ) {
			throw new MappingException(
					String.format(
							Locale.ENGLISH,
							"Entity [%s] did not define an identifier",
							rootEntitySource.getEntityNamingSource().getEntityName()
					),
					rootEntitySource.origin()
			);
		}

		if ( rootEntitySource.jaxbEntityMapping().getId() != null ) {
			return new IdentifierSourceSimpleImpl( rootEntitySource );
		}
		else {
			// if we get here, we should have a composite identifier.  Just need
			// to determine if it is aggregated, or non-aggregated...
			if ( StringHelper.isEmpty( rootEntitySource.jaxbEntityMapping().getCompositeId().getName() ) ) {
				if ( rootEntitySource.jaxbEntityMapping().getCompositeId().isMapped()
						&& StringHelper.isEmpty( rootEntitySource.jaxbEntityMapping().getCompositeId().getClazz() ) ) {
					throw new MappingException(
							"mapped composite identifier must name component class to use.",
							rootEntitySource.origin()
					);
				}
				return new IdentifierSourceNonAggregatedCompositeImpl( rootEntitySource );
			}
			else {
				if ( rootEntitySource.jaxbEntityMapping().getCompositeId().isMapped() ) {
					throw new MappingException(
							"cannot combine mapped=\"true\" with specified name",
							rootEntitySource.origin()
					);
				}
				return new IdentifierSourceAggregatedCompositeImpl( rootEntitySource );
			}
		}
	}

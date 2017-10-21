	@Override
	protected void buildAttributeSources(final AttributesHelper.Callback attributeBuildingCallback) {
		final JaxbHbmNaturalIdType naturalId = jaxbEntityMapping().getNaturalId();
		if ( naturalId != null ) {
			final NaturalIdMutability naturalIdMutability = naturalId.isMutable()
					? NaturalIdMutability.MUTABLE
					: NaturalIdMutability.IMMUTABLE;

			AttributesHelper.processAttributes(
					sourceMappingDocument(),
					attributeBuildingCallback,
					naturalId.getAttributes(),
					null,
					naturalIdMutability
			);
		}

		super.buildAttributeSources( attributeBuildingCallback );
	}

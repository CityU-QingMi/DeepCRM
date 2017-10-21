	private EntityType getEntityType() {
		return metadata.getTypeResolver().getTypeFactory().manyToOne(
				getReferencedEntityName(),
				true,
				null,
				false,
				false,
				isIgnoreNotFound(),
				false
		);
	}

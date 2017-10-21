	public static RelationDescription toMany(
			String fromPropertyName,
			RelationType relationType,
			String toEntityName,
			String mappedByPropertyName,
			IdMapper idMapper,
			PropertyMapper fakeBidirectionalRelationMapper,
			PropertyMapper fakeBidirectionalRelationIndexMapper,
			boolean insertable,
			boolean indexed) {
		// Envers populates collections by executing dedicated queries. Special handling of
		// @NotFound(action = NotFoundAction.IGNORE) can be omitted in such case as exceptions
		// (e.g. EntityNotFoundException, ObjectNotFoundException) are never thrown.
		// Therefore assigning false to ignoreNotFound.
		return new RelationDescription(
				fromPropertyName, relationType, toEntityName, mappedByPropertyName, idMapper, fakeBidirectionalRelationMapper,
				fakeBidirectionalRelationIndexMapper, insertable, false, indexed
		);
	}

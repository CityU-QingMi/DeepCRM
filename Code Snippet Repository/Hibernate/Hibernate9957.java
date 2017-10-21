	public static RelationDescription toOne(
			String fromPropertyName,
			RelationType relationType,
			String toEntityName,
			String mappedByPropertyName,
			IdMapper idMapper,
			PropertyMapper fakeBidirectionalRelationMapper,
			PropertyMapper fakeBidirectionalRelationIndexMapper,
			boolean insertable,
			boolean ignoreNotFound) {
		return new RelationDescription(
				fromPropertyName, relationType, toEntityName, mappedByPropertyName, idMapper,
				fakeBidirectionalRelationMapper, fakeBidirectionalRelationIndexMapper, insertable, ignoreNotFound, false
		);
	}

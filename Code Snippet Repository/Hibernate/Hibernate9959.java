	private RelationDescription(
			String fromPropertyName,
			RelationType relationType,
			String toEntityName,
			String mappedByPropertyName,
			IdMapper idMapper,
			PropertyMapper fakeBidirectionalRelationMapper,
			PropertyMapper fakeBidirectionalRelationIndexMapper,
			boolean insertable,
			boolean ignoreNotFound,
			boolean indexed) {
		this.fromPropertyName = fromPropertyName;
		this.relationType = relationType;
		this.toEntityName = toEntityName;
		this.mappedByPropertyName = mappedByPropertyName;
		this.ignoreNotFound = ignoreNotFound;
		this.idMapper = idMapper;
		this.fakeBidirectionalRelationMapper = fakeBidirectionalRelationMapper;
		this.fakeBidirectionalRelationIndexMapper = fakeBidirectionalRelationIndexMapper;
		this.insertable = insertable;
		this.indexed = indexed;
		this.bidirectional = false;
	}

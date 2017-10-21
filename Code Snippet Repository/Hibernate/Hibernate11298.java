	public MappedParentEntity(
			Long id,
			String grandparent,
			String notAudited,
			String parent,
			StrIntTestEntity relation) {
		super( id, grandparent, notAudited );
		this.parent = parent;
		this.relation = relation;
	}

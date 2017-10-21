	public BabyCompleteEntity(
			Long id,
			String grandparent,
			String notAudited,
			String parent,
			String child,
			StrIntTestEntity relation,
			String baby) {
		super( id, grandparent, notAudited, parent, child, relation );
		this.baby = baby;
	}

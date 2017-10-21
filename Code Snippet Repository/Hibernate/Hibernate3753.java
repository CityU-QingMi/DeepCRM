	@Override
	public ExpandingEntityQuerySpace makeEntityQuerySpace(
			String uid,
			EntityPersister entityPersister,
			boolean canJoinsBeRequired) {

		checkQuerySpaceDoesNotExist( uid );

		// as a temporary fix for HHH-8980 and HHH-8830 we circumvent allowing
		// inner joins (canJoinsBeRequired) when the persister is part of an
		// entity inheritance.
		//
		// hasSubclasses() is the closest we can come to even knowing if the
		// entity is part of a hierarchy.  But its enough, since if there are no
		// subclasses we cannot have the case where the attribute to join comes from
		// a subclass :)
		//
		// a better long term fix is to expose whether a joined association attribute
		// is defined on the class/superClass(es) or on the subClass(es).  Attributes
		// from the subClass(es) should not be inner joined; it is ok to inner join
		// attributes from the class/superClass(es).

		final EntityQuerySpaceImpl space = new EntityQuerySpaceImpl(
				entityPersister,
				uid,
				this,
				canJoinsBeRequired && !entityPersister.getEntityMetamodel().hasSubclasses()
		);
		registerQuerySpace( space );

		return space;
	}

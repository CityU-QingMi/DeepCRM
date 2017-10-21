	protected boolean visitCollectionsBeforeSave(
			Object entity,
			Serializable id,
			Object[] values,
			Type[] types,
			EventSource source) {
		WrapVisitor visitor = new WrapVisitor( source );
		// substitutes into values by side-effect
		visitor.processEntityPropertyValues( values, types );
		return visitor.isSubstitutionRequired();
	}

	@Override
	protected boolean visitCollectionsBeforeSave(
			Object entity,
			Serializable id,
			Object[] values,
			Type[] types,
			EventSource source) {
		//TODO: we use two visitors here, inefficient!
		OnReplicateVisitor visitor = new OnReplicateVisitor( source, id, entity, false );
		visitor.processEntityPropertyValues( values, types );
		return super.visitCollectionsBeforeSave( entity, id, values, types, source );
	}

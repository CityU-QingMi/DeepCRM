	@Override
	public void setDeletedState(Object[] deletedState) {
		EntityEntryExtraStateHolder extra = getExtraState( EntityEntryExtraStateHolder.class );
		if ( extra == null && deletedState == DEFAULT_DELETED_STATE ) {
			//this is the default value and we do not store the extra state
			return;
		}
		if ( extra == null ) {
			extra = new EntityEntryExtraStateHolder();
			addExtraState( extra );
		}
		extra.setDeletedState( deletedState );
	}

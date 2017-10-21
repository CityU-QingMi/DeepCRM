	@Override
	public final void setReadOnly(boolean readOnly) {
		errorIfReadOnlySettingNotAvailable();
		// only update if readOnly is different from current setting
		if ( this.readOnly != readOnly ) {
			final EntityPersister persister = session.getFactory().getEntityPersister( entityName );
			if ( !persister.isMutable() && !readOnly ) {
				throw new IllegalStateException( "cannot make proxies for immutable entities modifiable" );
			}
			this.readOnly = readOnly;
			if ( initialized ) {
				EntityKey key = generateEntityKeyOrNull( getIdentifier(), session, getEntityName() );
				if ( key != null && session.getPersistenceContext().containsEntity( key ) ) {
					session.getPersistenceContext().setReadOnly( target, readOnly );
				}
			}
		}
	}

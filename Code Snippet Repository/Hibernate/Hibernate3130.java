		@SuppressWarnings("")
		protected T doGetReference(Serializable id) {
			if ( this.lockOptions != null ) {
				LoadEvent event = new LoadEvent( id, entityPersister.getEntityName(), lockOptions, SessionImpl.this );
				fireLoad( event, LoadEventListener.LOAD );
				return (T) event.getResult();
			}

			LoadEvent event = new LoadEvent( id, entityPersister.getEntityName(), false, SessionImpl.this );
			boolean success = false;
			try {
				fireLoad( event, LoadEventListener.LOAD );
				if ( event.getResult() == null ) {
					getFactory().getEntityNotFoundDelegate().handleEntityNotFound(
							entityPersister.getEntityName(),
							id
					);
				}
				success = true;
				return (T) event.getResult();
			}
			finally {
				afterOperation( success );
			}
		}

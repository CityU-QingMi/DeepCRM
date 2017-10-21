		@Override
		@SuppressWarnings("")
		public T load(Object naturalIdValue) {
			final Serializable entityId = resolveNaturalId( getNaturalIdParameters( naturalIdValue ) );
			if ( entityId == null ) {
				return null;
			}
			try {
				return (T) this.getIdentifierLoadAccess().load( entityId );
			}
			catch (EntityNotFoundException | ObjectNotFoundException e) {
				// OK
			}
			return null;
		}

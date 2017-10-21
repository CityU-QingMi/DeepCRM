		@Override
		@SuppressWarnings("")
		public final T load() {
			final Serializable entityId = resolveNaturalId( this.naturalIdParameters );
			if ( entityId == null ) {
				return null;
			}
			try {
				return (T) this.getIdentifierLoadAccess().load( entityId );
			}
			catch (EntityNotFoundException | ObjectNotFoundException enf) {
				// OK
			}
			return null;
		}

		public boolean canProcessImmediately() {
			// We can process the FK immediately if it is a reference to the associated
			// entity's PK.
			//
			// There is an assumption here that the columns making up the FK have been bound.
			// We assume the caller checks that
			final PersistentClass referencedEntityBinding = mappingDocument.getMetadataCollector()
					.getEntityBinding( referencedEntityName );
			return referencedEntityBinding != null && referencedEntityAttributeName != null;

		}

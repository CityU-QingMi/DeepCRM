		public boolean canProcessImmediately() {
			if ( allColumnsNamed ) {
				return true;
			}

			final PersistentClass referencedEntityBinding = mappingDocument.getMetadataCollector()
					.getEntityBinding( referencedEntityName );
			if ( referencedEntityBinding == null ) {
				return false;
			}

			// for implicit naming, we can do it immediately if the associated entity
			// is bound and the reference is to its PK.  For property-refs, we'd have to
			// be *sure* that the column(s) for the referenced property is fully bound
			// and we just cannot know that in today's model

			return manyToOneSource.getReferencedEntityAttributeName() == null;
		}

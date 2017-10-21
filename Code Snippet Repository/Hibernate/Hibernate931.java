		public ManyToOneFkSecondPass(
				MappingDocument mappingDocument,
				SingularAttributeSourceManyToOne manyToOneSource,
				ManyToOne manyToOneBinding,
				String referencedEntityName) {
			super( manyToOneBinding, null );

			if ( referencedEntityName == null ) {
				throw new MappingException(
						"entity name referenced by many-to-one required [" + manyToOneSource.getAttributeRole().getFullPath() + "]",
						mappingDocument.getOrigin()
				);
			}
			this.mappingDocument = mappingDocument;
			this.manyToOneBinding = manyToOneBinding;
			this.referencedEntityName = referencedEntityName;

			this.referencedEntityAttributeName = manyToOneSource.getReferencedEntityAttributeName();
		}

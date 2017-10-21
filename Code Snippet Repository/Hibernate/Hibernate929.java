		public ManyToOneColumnBinder(
				MappingDocument mappingDocument,
				SingularAttributeSourceManyToOne manyToOneSource,
				ManyToOne manyToOneBinding,
				String referencedEntityName) {
			this.mappingDocument = mappingDocument;
			this.manyToOneSource = manyToOneSource;
			this.manyToOneBinding = manyToOneBinding;
			this.referencedEntityName = referencedEntityName;

			boolean allNamed = true;
			for ( RelationalValueSource relationalValueSource : manyToOneSource.getRelationalValueSources() ) {
				if ( relationalValueSource instanceof ColumnSource ) {
					if ( ( (ColumnSource) relationalValueSource ).getName() == null ) {
						allNamed = false;
						break;
					}
				}
			}
			this.allColumnsNamed = allNamed;
		}

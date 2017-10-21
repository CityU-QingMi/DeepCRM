		@Override
		public void process() {
			log.debugf( "Binding natural-id UniqueKey for entity : " + entityBinding.getEntityName() );

			final List<Identifier> columnNames = new ArrayList<Identifier>();

			final UniqueKey uk = new UniqueKey();
			uk.setTable( entityBinding.getTable() );
			for ( Property attributeBinding : attributeBindings ) {
				final Iterator itr = attributeBinding.getColumnIterator();
				while ( itr.hasNext() ) {
					final Object selectable = itr.next();
					if ( Column.class.isInstance( selectable ) ) {
						final Column column = (Column) selectable;
						uk.addColumn( column );
						columnNames.add(
								mappingDocument.getMetadataCollector().getDatabase().toIdentifier( column.getQuotedName() )
						);
					}
				}
				uk.addColumns( attributeBinding.getColumnIterator() );
			}

			final Identifier ukName = mappingDocument.getBuildingOptions().getImplicitNamingStrategy().determineUniqueKeyName(
					new ImplicitUniqueKeyNameSource() {
						@Override
						public Identifier getTableName() {
							return entityBinding.getTable().getNameIdentifier();
						}

						@Override
						public List<Identifier> getColumnNames() {
							return columnNames;
						}

						@Override
						public MetadataBuildingContext getBuildingContext() {
							return mappingDocument;
						}

						@Override
						public Identifier getUserProvidedIdentifier() {
							return uk.getName() != null ? Identifier.toIdentifier( uk.getName() ) : null;
						}
					}
			);
			uk.setName( ukName.render( mappingDocument.getMetadataCollector().getDatabase().getDialect() ) );

			entityBinding.getTable().addUniqueKey( uk );
		}

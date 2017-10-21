		@Override
		protected void createBackReferences() {
			super.createBackReferences();

			boolean indexIsFormula = false;
			Iterator itr = getCollectionBinding().getIndex().getColumnIterator();
			while ( itr.hasNext() ) {
				if ( ( (Selectable) itr.next() ).isFormula() ) {
					indexIsFormula = true;
				}
			}

			if ( getCollectionBinding().isOneToMany()
					&& !getCollectionBinding().getKey().isNullable()
					&& !getCollectionBinding().isInverse()
					&& !indexIsFormula ) {
				final String entityName = ( (OneToMany) getCollectionBinding().getElement() ).getReferencedEntityName();
				final PersistentClass referenced = getMappingDocument().getMetadataCollector().getEntityBinding( entityName );
				final IndexBackref ib = new IndexBackref();
				ib.setName( '_' + getCollectionBinding().getOwnerEntityName() + "." + getPluralAttributeSource().getName() + "IndexBackref" );
				ib.setUpdateable( false );
				ib.setSelectable( false );
				ib.setCollectionRole( getCollectionBinding().getRole() );
				ib.setEntityName( getCollectionBinding().getOwner().getEntityName() );
				ib.setValue( getCollectionBinding().getIndex() );
				referenced.addProperty( ib );
			}
		}

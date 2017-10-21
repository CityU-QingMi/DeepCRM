		protected void createBackReferences() {
			if ( collectionBinding.isOneToMany()
					&& !collectionBinding.isInverse()
					&& !collectionBinding.getKey().isNullable() ) {
				// for non-inverse one-to-many, with a not-null fk, add a backref!
				String entityName = ( (OneToMany) collectionBinding.getElement() ).getReferencedEntityName();
				PersistentClass referenced = mappingDocument.getMetadataCollector().getEntityBinding( entityName );
				Backref prop = new Backref();
				prop.setName( '_' + collectionBinding.getOwnerEntityName() + "." + pluralAttributeSource.getName() + "Backref" );
				prop.setUpdateable( false );
				prop.setSelectable( false );
				prop.setCollectionRole( collectionBinding.getRole() );
				prop.setEntityName( collectionBinding.getOwner().getEntityName() );
				prop.setValue( collectionBinding.getKey() );
				referenced.addProperty( prop );

				log.debugf(
						"Added virtual backref property [%s] : %s",
						prop.getName(),
						pluralAttributeSource.getAttributeRole().getFullPath()
				);
			}
		}

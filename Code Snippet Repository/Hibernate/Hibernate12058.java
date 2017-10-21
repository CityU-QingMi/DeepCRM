	protected final void applyCacheSettings(Metadata metadata) {
		if ( !overrideCacheStrategy() ) {
			return;
		}

		if ( getCacheConcurrencyStrategy() == null ) {
			return;
		}

		for ( PersistentClass entityBinding : metadata.getEntityBindings() ) {
			if ( entityBinding.isInherited() ) {
				continue;
			}

			boolean hasLob = false;

			final Iterator props = entityBinding.getPropertyClosureIterator();
			while ( props.hasNext() ) {
				final Property prop = (Property) props.next();
				if ( prop.getValue().isSimpleValue() ) {
					if ( isLob( ( (SimpleValue) prop.getValue() ).getTypeName() ) ) {
						hasLob = true;
						break;
					}
				}
			}

			if ( !hasLob ) {
				( ( RootClass) entityBinding ).setCacheConcurrencyStrategy( getCacheConcurrencyStrategy() );
			}
		}

		for ( Collection collectionBinding : metadata.getCollectionBindings() ) {
			boolean isLob = false;

			if ( collectionBinding.getElement().isSimpleValue() ) {
				isLob = isLob( ( (SimpleValue) collectionBinding.getElement() ).getTypeName() );
			}

			if ( !isLob ) {
				collectionBinding.setCacheConcurrencyStrategy( getCacheConcurrencyStrategy() );
			}
		}
	}

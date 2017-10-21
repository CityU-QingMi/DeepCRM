	@Override
	public void prepareForDot(String propertyName) throws SemanticException {
		FromElement fromElement = getFromElement();
		if ( fromElement == null ) {
			throw new IllegalStateException( "No FROM element for index operator!" );
		}

		final QueryableCollection queryableCollection = fromElement.getQueryableCollection();
		if ( queryableCollection != null && !queryableCollection.isOneToMany() ) {
			final FromReferenceNode collectionNode = (FromReferenceNode) getFirstChild();
			final String path = collectionNode.getPath() + "[]." + propertyName;
			LOG.debugf( "Creating join for many-to-many elements for %s", path );
			final FromElementFactory factory = new FromElementFactory( fromElement.getFromClause(), fromElement, path );
			// This will add the new from element to the origin.
			final FromElement elementJoin = factory.createElementJoin( queryableCollection );
			setFromElement( elementJoin );
		}
	}

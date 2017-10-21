	@SuppressWarnings({""})
	private void addRelatedToXmlMapping(
			Element xmlMapping,
			String prefix,
			MetadataTools.ColumnNameIterator columnNameIterator,
			IdMappingData relatedIdMapping) {
		final Element properties = (Element) relatedIdMapping.getXmlRelationMapping().clone();
		MetadataTools.prefixNamesInPropertyElement( properties, prefix, columnNameIterator, true, true );
		for ( Element idProperty : (java.util.List<Element>) properties.elements() ) {
			xmlMapping.add( (Element) idProperty.clone() );
		}
	}

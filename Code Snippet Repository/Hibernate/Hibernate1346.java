	private void preFilter(List<XProperty> fields, List<XProperty> getters) {
		Iterator<XProperty> propertyIterator = fields.iterator();
		while ( propertyIterator.hasNext() ) {
			final XProperty property = propertyIterator.next();
			if ( mustBeSkipped( property ) ) {
				propertyIterator.remove();
			}
		}

		propertyIterator = getters.iterator();
		while ( propertyIterator.hasNext() ) {
			final XProperty property = propertyIterator.next();
			if ( mustBeSkipped( property ) ) {
				propertyIterator.remove();
			}
		}
	}

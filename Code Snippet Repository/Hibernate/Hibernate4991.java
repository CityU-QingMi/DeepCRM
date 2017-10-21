	private boolean hasPartialInsertComponentGeneration(Component component) {
		Iterator subProperties = component.getPropertyIterator();
		while ( subProperties.hasNext() ) {
			final Property prop = ( Property ) subProperties.next();
			if ( isInsertGenerated( prop ) ) {
				return true;
			}
			else if ( prop.getValue() instanceof Component ) {
				if ( hasPartialInsertComponentGeneration( (Component) prop.getValue() ) ) {
					return true;
				}
			}
		}
		return false;
	}

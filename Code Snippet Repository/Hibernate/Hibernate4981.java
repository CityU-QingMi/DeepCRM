	private boolean hasPartialUpdateComponentGeneration(Component component) {
		Iterator subProperties = component.getPropertyIterator();
		while ( subProperties.hasNext() ) {
			Property prop = (Property) subProperties.next();
			if ( isUpdateGenerated( prop ) ) {
				return true;
			}
			else if ( prop.getValue() instanceof Component ) {
				if ( hasPartialUpdateComponentGeneration( ( Component ) prop.getValue() ) ) {
					return true;
				}
			}
		}
		return false;
	}

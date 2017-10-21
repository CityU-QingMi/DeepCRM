	private List<String> addEntityListenerClasses(Element element, String packageName, List<String> addedClasses) {
		List<String> localAddedClasses = new ArrayList<String>();
		Element listeners = element.element( "entity-listeners" );
		if ( listeners != null ) {
			@SuppressWarnings( "unchecked" )
			List<Element> elements = listeners.elements( "entity-listener" );
			for (Element listener : elements) {
				String listenerClassName = buildSafeClassName( listener.attributeValue( "class" ), packageName );
				if ( classOverriding.containsKey( listenerClassName ) ) {
					//maybe switch it to warn?
					if ( "entity-listener".equals( classOverriding.get( listenerClassName ).getName() ) ) {
						LOG.duplicateListener( listenerClassName );
						continue;
					}
					throw new IllegalStateException("Duplicate XML entry for " + listenerClassName);
				}
				localAddedClasses.add( listenerClassName );
				classOverriding.put( listenerClassName, listener );
			}
		}
		LOG.debugf( "Adding XML overriding information for listeners: %s", localAddedClasses );
		addedClasses.addAll( localAddedClasses );
		return localAddedClasses;
	}

	@Override
	protected EntityPersister locateOwningPersister() {
		final AbstractCompositionAttribute source = (AbstractCompositionAttribute) getSource();
		if ( EntityDefinition.class.isInstance( source.getSource() ) ) {
			return EntityDefinition.class.cast( source.getSource() ).getEntityPersister();
		}
		else {
			return AbstractCompositionAttribute.class.cast( source.getSource() ).locateOwningPersister();
		}
	}

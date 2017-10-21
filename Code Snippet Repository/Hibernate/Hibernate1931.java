	private static void cascadeAssociation(
			final CascadingAction action,
			final CascadePoint cascadePoint,
			final EventSource eventSource,
			final int componentPathStackDepth,
			final Object parent,
			final Object child,
			final Type type,
			final CascadeStyle style,
			final Object anything,
			final boolean isCascadeDeleteEnabled) {
		if ( type.isEntityType() || type.isAnyType() ) {
			cascadeToOne( action, eventSource, parent, child, type, style, anything, isCascadeDeleteEnabled );
		}
		else if ( type.isCollectionType() ) {
			cascadeCollection(
					action,
					cascadePoint,
					eventSource,
					componentPathStackDepth,
					parent,
					child,
					style,
					anything,
					(CollectionType) type
			);
		}
	}

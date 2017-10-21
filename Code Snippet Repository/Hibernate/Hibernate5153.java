	public static Object[] replaceAssociations(
			final Object[] original,
			final Object[] target,
			final Type[] types,
			final SharedSessionContractImplementor session,
			final Object owner,
			final Map copyCache,
			final ForeignKeyDirection foreignKeyDirection) {
		Object[] copied = new Object[original.length];
		for ( int i = 0; i < types.length; i++ ) {
			if ( original[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY
					|| original[i] == PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
				copied[i] = target[i];
			}
			else if ( types[i].isComponentType() ) {
				// need to extract the component values and check for subtype replacements...
				CompositeType componentType = ( CompositeType ) types[i];
				Type[] subtypes = componentType.getSubtypes();
				Object[] origComponentValues = original[i] == null ? new Object[subtypes.length] : componentType.getPropertyValues( original[i], session );
				Object[] targetComponentValues = target[i] == null ? new Object[subtypes.length] : componentType.getPropertyValues( target[i], session );
				replaceAssociations( origComponentValues, targetComponentValues, subtypes, session, null, copyCache, foreignKeyDirection );
				copied[i] = target[i];
			}
			else if ( !types[i].isAssociationType() ) {
				copied[i] = target[i];
			}
			else {
				copied[i] = types[i].replace( original[i], target[i], session, owner, copyCache, foreignKeyDirection );
			}
		}
		return copied;
	}

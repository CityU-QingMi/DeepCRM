	private static void collectNonNullableTransientEntities(
			Nullifier nullifier,
			Object value,
			String propertyName,
			Type type,
			boolean isNullable,
			SharedSessionContractImplementor session,
			NonNullableTransientDependencies nonNullableTransientEntities) {
		if ( value == null ) {
			return;
		}

		if ( type.isEntityType() ) {
			final EntityType entityType = (EntityType) type;
			if ( !isNullable
					&& !entityType.isOneToOne()
					&& nullifier.isNullifiable( entityType.getAssociatedEntityName(), value ) ) {
				nonNullableTransientEntities.add( propertyName, value );
			}
		}
		else if ( type.isAnyType() ) {
			if ( !isNullable && nullifier.isNullifiable( null, value ) ) {
				nonNullableTransientEntities.add( propertyName, value );
			}
		}
		else if ( type.isComponentType() ) {
			final CompositeType actype = (CompositeType) type;
			final boolean[] subValueNullability = actype.getPropertyNullability();
			if ( subValueNullability != null ) {
				final String[] subPropertyNames = actype.getPropertyNames();
				final Object[] subvalues = actype.getPropertyValues( value, session );
				final Type[] subtypes = actype.getSubtypes();
				for ( int j = 0; j < subvalues.length; j++ ) {
					collectNonNullableTransientEntities(
							nullifier,
							subvalues[j],
							subPropertyNames[j],
							subtypes[j],
							subValueNullability[j],
							session,
							nonNullableTransientEntities
					);
				}
			}
		}
	}

		private Object nullifyTransientReferences(final Object value, final Type type) {
			if ( value == null ) {
				return null;
			}
			else if ( type.isEntityType() ) {
				final EntityType entityType = (EntityType) type;
				if ( entityType.isOneToOne() ) {
					return value;
				}
				else {
					final String entityName = entityType.getAssociatedEntityName();
					return isNullifiable( entityName, value ) ? null : value;
				}
			}
			else if ( type.isAnyType() ) {
				return isNullifiable( null, value ) ? null : value;
			}
			else if ( type.isComponentType() ) {
				final CompositeType actype = (CompositeType) type;
				final Object[] subvalues = actype.getPropertyValues( value, session );
				final Type[] subtypes = actype.getSubtypes();
				boolean substitute = false;
				for ( int i = 0; i < subvalues.length; i++ ) {
					final Object replacement = nullifyTransientReferences( subvalues[i], subtypes[i] );
					if ( replacement != subvalues[i] ) {
						substitute = true;
						subvalues[i] = replacement;
					}
				}
				if ( substitute ) {
					// todo : need to account for entity mode on the CompositeType interface :(
					actype.setPropertyValues( value, subvalues, EntityMode.POJO );
				}
				return value;
			}
			else {
				return value;
			}
		}

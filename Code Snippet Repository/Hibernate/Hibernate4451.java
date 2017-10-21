	@Override
	@SuppressWarnings("")
	protected ManagedType<? super X> locateManagedType() {
		if ( getModel().getBindableType() == Bindable.BindableType.ENTITY_TYPE ) {
			return (ManagedType<? super X>) getModel();
		}
		else if ( getModel().getBindableType() == Bindable.BindableType.SINGULAR_ATTRIBUTE ) {
			final Type joinedAttributeType = ( (SingularAttribute) getAttribute() ).getType();
			if ( !ManagedType.class.isInstance( joinedAttributeType ) ) {
				throw new UnsupportedOperationException(
						"Cannot further dereference attribute join [" + getPathIdentifier() + "] as its type is not a ManagedType"
				);
			}
			return (ManagedType<? super X>) joinedAttributeType;
		}
		else if ( getModel().getBindableType() == Bindable.BindableType.PLURAL_ATTRIBUTE ) {
			final Type elementType = ( (PluralAttribute) getAttribute() ).getElementType();
			if ( !ManagedType.class.isInstance( elementType ) ) {
				throw new UnsupportedOperationException(
						"Cannot further dereference attribute join [" + getPathIdentifier() + "] (plural) as its element type is not a ManagedType"
				);
			}
			return (ManagedType<? super X>) elementType;
		}

		return super.locateManagedType();
	}

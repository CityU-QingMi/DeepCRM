	@Override
	public boolean isCompilable() {
		if (this.indexedType == IndexedType.ARRAY) {
			return (this.exitTypeDescriptor != null);
		}
		else if (this.indexedType == IndexedType.LIST) {
			return this.children[0].isCompilable();
		}
		else if (this.indexedType == IndexedType.MAP) {
			return (this.children[0] instanceof PropertyOrFieldReference || this.children[0].isCompilable());
		}
		else if (this.indexedType == IndexedType.OBJECT) {
			// If the string name is changing the accessor is clearly going to change (so compilation is not possible)
			if (this.cachedReadAccessor != null && 
					(this.cachedReadAccessor instanceof ReflectivePropertyAccessor.OptimalPropertyAccessor) &&
					(getChild(0) instanceof StringLiteral)) {
				return true;
			}
		}
		return false;
	}

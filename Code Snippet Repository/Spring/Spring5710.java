		@Override
		public TypedValue getValue() {
			TypedValue value =
					this.ref.getValueInternal(this.contextObject, this.evalContext, this.autoGrowNullReferences);
			PropertyAccessor accessorToUse = this.ref.cachedReadAccessor;
			if (accessorToUse instanceof CompilablePropertyAccessor) {
				this.ref.exitTypeDescriptor =
						CodeFlow.toDescriptor(((CompilablePropertyAccessor) accessorToUse).getPropertyType());
			}
			return value;
		}

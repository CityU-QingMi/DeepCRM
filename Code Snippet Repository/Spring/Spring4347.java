		@Override
		@Nullable
		public ResolvableType resolveVariable(TypeVariable<?> variable) {
			for (int i = 0; i < this.variables.length; i++) {
				if (ObjectUtils.nullSafeEquals(SerializableTypeWrapper.unwrap(this.variables[i]),
						SerializableTypeWrapper.unwrap(variable))) {
					return this.generics[i];
				}
			}
			return null;
		}

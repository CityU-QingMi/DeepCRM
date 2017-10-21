		@Override
		public TypedValue read(EvaluationContext context, @Nullable Object target, String name) throws AccessException {
			if (this.member instanceof Method) {
				Method method = (Method) this.member;
				try {
					ReflectionUtils.makeAccessible(method);
					Object value = method.invoke(target);
					return new TypedValue(value, this.typeDescriptor.narrow(value));
				}
				catch (Exception ex) {
					throw new AccessException("Unable to access property '" + name + "' through getter method", ex);
				}
			}
			else {
				Field field = (Field) this.member;
				try {
					ReflectionUtils.makeAccessible(field);
					Object value = field.get(target);
					return new TypedValue(value, this.typeDescriptor.narrow(value));
				}
				catch (Exception ex) {
					throw new AccessException("Unable to access field '" + name + "'", ex);
				}
			}
		}

		@Override
		public boolean canRead(EvaluationContext context, @Nullable Object target, String name) throws AccessException {
			if (target == null) {
				return false;
			}
			Class<?> type = (target instanceof Class ? (Class<?>) target : target.getClass());
			if (type.isArray()) {
				return false;
			}

			if (this.member instanceof Method) {
				Method method = (Method) this.member;
				String getterName = "get" + StringUtils.capitalize(name);
				if (getterName.equals(method.getName())) {
					return true;
				}
				getterName = "is" + StringUtils.capitalize(name);
				return getterName.equals(method.getName());
			}
			else {
				Field field = (Field) this.member;
				return field.getName().equals(name);
			}
		}

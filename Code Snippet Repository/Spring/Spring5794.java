	public PropertyAccessor createOptimalAccessor(EvaluationContext evalContext, @Nullable Object target, String name) {
		// Don't be clever for arrays or null target
		if (target == null) {
			return this;
		}
		Class<?> type = (target instanceof Class ? (Class<?>) target : target.getClass());
		if (type.isArray()) {
			return this;
		}

		PropertyCacheKey cacheKey = new PropertyCacheKey(type, name, target instanceof Class);
		InvokerPair invocationTarget = this.readerCache.get(cacheKey);

		if (invocationTarget == null || invocationTarget.member instanceof Method) {
			Method method = (Method) (invocationTarget != null ? invocationTarget.member : null);
			if (method == null) {
				method = findGetterForProperty(name, type, target);
				if (method != null) {
					invocationTarget = new InvokerPair(method, new TypeDescriptor(new MethodParameter(method, -1)));
					ReflectionUtils.makeAccessible(method);
					this.readerCache.put(cacheKey, invocationTarget);
				}
			}
			if (method != null) {
				return new OptimalPropertyAccessor(invocationTarget);
			}
		}

		if (invocationTarget == null || invocationTarget.member instanceof Field) {
			Field field = (invocationTarget != null ? (Field) invocationTarget.member : null);
			if (field == null) {
				field = findField(name, type, target instanceof Class);
				if (field != null) {
					invocationTarget = new InvokerPair(field, new TypeDescriptor(field));
					ReflectionUtils.makeAccessible(field);
					this.readerCache.put(cacheKey, invocationTarget);
				}
			}
			if (field != null) {
				return new OptimalPropertyAccessor(invocationTarget);
			}
		}

		return this;
	}

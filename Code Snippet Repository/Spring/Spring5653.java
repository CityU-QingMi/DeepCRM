		@Override
		public void setValue(@Nullable Object newValue) {
			Class<?> contextObjectClass = getObjectClass(this.targetObject);
			try {
				if (Indexer.this.cachedWriteName != null && Indexer.this.cachedWriteName.equals(this.name) &&
						Indexer.this.cachedWriteTargetType != null &&
						Indexer.this.cachedWriteTargetType.equals(contextObjectClass)) {
					// It is OK to use the cached accessor
					PropertyAccessor accessor = Indexer.this.cachedWriteAccessor;
					Assert.state(accessor != null, "No cached write accessor");
					accessor.write(this.evaluationContext, this.targetObject, this.name, newValue);
					return;
				}
				List<PropertyAccessor> accessorsToTry =
						AstUtils.getPropertyAccessorsToTry(contextObjectClass, this.evaluationContext.getPropertyAccessors());
				for (PropertyAccessor accessor : accessorsToTry) {
					if (accessor.canWrite(this.evaluationContext, this.targetObject, this.name)) {
						Indexer.this.cachedWriteName = this.name;
						Indexer.this.cachedWriteTargetType = contextObjectClass;
						Indexer.this.cachedWriteAccessor = accessor;
						accessor.write(this.evaluationContext, this.targetObject, this.name, newValue);
						return;
					}
				}
			}
			catch (AccessException ex) {
				throw new SpelEvaluationException(getStartPosition(), ex, SpelMessage.EXCEPTION_DURING_PROPERTY_WRITE,
						this.name, ex.getMessage());
			}
		}

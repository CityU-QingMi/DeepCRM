		@Override
		public TypedValue getValue() {
			Class<?> targetObjectRuntimeClass = getObjectClass(this.targetObject);
			try {
				if (Indexer.this.cachedReadName != null && Indexer.this.cachedReadName.equals(this.name) &&
						Indexer.this.cachedReadTargetType != null &&
						Indexer.this.cachedReadTargetType.equals(targetObjectRuntimeClass)) {
					// It is OK to use the cached accessor
					PropertyAccessor accessor = Indexer.this.cachedReadAccessor;
					Assert.state(accessor != null, "No cached read accessor");
					return accessor.read(this.evaluationContext, this.targetObject, this.name);
				}
				List<PropertyAccessor> accessorsToTry = AstUtils.getPropertyAccessorsToTry(
						targetObjectRuntimeClass, this.evaluationContext.getPropertyAccessors());
				for (PropertyAccessor accessor : accessorsToTry) {
					if (accessor.canRead(this.evaluationContext, this.targetObject, this.name)) {
						if (accessor instanceof ReflectivePropertyAccessor) {
							accessor = ((ReflectivePropertyAccessor) accessor).createOptimalAccessor(
									this.evaluationContext, this.targetObject, this.name);
						}
						Indexer.this.cachedReadAccessor = accessor;
						Indexer.this.cachedReadName = this.name;
						Indexer.this.cachedReadTargetType = targetObjectRuntimeClass;
						if (accessor instanceof ReflectivePropertyAccessor.OptimalPropertyAccessor) {
							ReflectivePropertyAccessor.OptimalPropertyAccessor optimalAccessor =
									(ReflectivePropertyAccessor.OptimalPropertyAccessor) accessor;
							Member member = optimalAccessor.member;
							Indexer.this.exitTypeDescriptor = CodeFlow.toDescriptor(member instanceof Method ?
									((Method) member).getReturnType() : ((Field) member).getType());
						}
						return accessor.read(this.evaluationContext, this.targetObject, this.name);
					}
				}
			}
			catch (AccessException ex) {
				throw new SpelEvaluationException(getStartPosition(), ex, SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE,
						this.targetObjectTypeDescriptor.toString());
			}
			throw new SpelEvaluationException(getStartPosition(), SpelMessage.INDEXING_NOT_SUPPORTED_FOR_TYPE,
					this.targetObjectTypeDescriptor.toString());
		}

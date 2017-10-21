	private TypedValue getValueInternal(TypedValue contextObject, EvaluationContext evalContext,
			boolean isAutoGrowNullReferences) throws EvaluationException {

		TypedValue result = readProperty(contextObject, evalContext, this.name);

		// Dynamically create the objects if the user has requested that optional behavior
		if (result.getValue() == null && isAutoGrowNullReferences &&
				nextChildIs(Indexer.class, PropertyOrFieldReference.class)) {
			TypeDescriptor resultDescriptor = result.getTypeDescriptor();
			Assert.state(resultDescriptor != null, "No result type");
			// Create a new collection or map ready for the indexer
			if (List.class == resultDescriptor.getType()) {
				if (isWritableProperty(this.name, contextObject, evalContext)) {
					List<?> newList = new ArrayList<>();
					writeProperty(contextObject, evalContext, this.name, newList);
					result = readProperty(contextObject, evalContext, this.name);
				}
			}
			else if (Map.class == resultDescriptor.getType()) {
				if (isWritableProperty(this.name,contextObject, evalContext)) {
					Map<?,?> newMap = new HashMap<>();
					writeProperty(contextObject, evalContext, this.name, newMap);
					result = readProperty(contextObject, evalContext, this.name);
				}
			}
			else {
				// 'simple' object
				try {
					if (isWritableProperty(this.name,contextObject, evalContext)) {
						Class<?> clazz = result.getTypeDescriptor().getType();
						Object newObject = ReflectionUtils.accessibleConstructor(clazz).newInstance();
						writeProperty(contextObject, evalContext, this.name, newObject);
						result = readProperty(contextObject, evalContext, this.name);
					}
				}
				catch (InvocationTargetException ex) {
					throw new SpelEvaluationException(getStartPosition(), ex.getTargetException(),
							SpelMessage.UNABLE_TO_DYNAMICALLY_CREATE_OBJECT, result.getTypeDescriptor().getType());
				}
				catch (Throwable ex) {
					throw new SpelEvaluationException(getStartPosition(), ex,
							SpelMessage.UNABLE_TO_DYNAMICALLY_CREATE_OBJECT, result.getTypeDescriptor().getType());
				}
			}
		}
		return result;
	}

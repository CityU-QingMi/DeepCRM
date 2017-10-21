	private void populateReferenceTypeArray(ExpressionState state, Object newArray, TypeConverter typeConverter,
			InlineList initializer, Class<?> componentType) {

		TypeDescriptor toTypeDescriptor = TypeDescriptor.valueOf(componentType);
		Object[] newObjectArray = (Object[]) newArray;
		for (int i = 0; i < newObjectArray.length; i++) {
			SpelNode elementNode = initializer.getChild(i);
			Object arrayEntry = elementNode.getValue(state);
			newObjectArray[i] = typeConverter.convertValue(arrayEntry,
					TypeDescriptor.forObject(arrayEntry), toTypeDescriptor);
		}
	}

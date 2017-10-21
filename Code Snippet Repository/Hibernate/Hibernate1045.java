	static MethodDescription getterOf(FieldDescription persistentField) {
		MethodList<?> methodList = MethodGraph.Compiler.DEFAULT.compile( persistentField.getDeclaringType().asErasure() )
				.listNodes()
				.asMethodList()
				.filter( isGetter(persistentField.getName() ) );
		if ( methodList.size() == 1 ) {
			return methodList.getOnly();
		}
		else {
			return null;
		}
	}

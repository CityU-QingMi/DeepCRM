		private String resolveExpression(A annotation) throws Exception {
			for (String methodName : EXPRESSION_PROPERTIES) {
				Method method;
				try {
					method = annotation.getClass().getDeclaredMethod(methodName);
				}
				catch (NoSuchMethodException ex) {
					method = null;
				}
				if (method != null) {
					String candidate = (String) method.invoke(annotation);
					if (StringUtils.hasText(candidate)) {
						return candidate;
					}
				}
			}
			throw new IllegalStateException("Failed to resolve expression: " + annotation);
		}

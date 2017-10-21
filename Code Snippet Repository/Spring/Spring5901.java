		private boolean isAnnotated(Method method) {
			Annotation[] anns = method.getAnnotations();
			if (anns == null) {
				return false;
			}
			for (Annotation ann : anns) {
				String name = ann.annotationType().getName();
				if (name.endsWith("Anno")) {
					return true;
				}
			}
			return false;
		}

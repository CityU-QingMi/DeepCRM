		private static List<Class<?>> findClassHierarchy(Class<?> clazz) {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			Class<?> current = clazz;
			do {
				classes.add( current );
				current = current.getSuperclass();
			} while ( current != null );

			return classes;
		}

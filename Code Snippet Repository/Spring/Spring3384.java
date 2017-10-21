		@Override
		public Object get(String name, ObjectFactory<?> objectFactory) {
			if (createNewScope) {
				beans.clear();
				// reset the flag back
				createNewScope = false;
			}

			Object bean = beans.get(name);
			// if a new object is requested or none exists under the current
			// name, create one
			if (bean == null) {
				beans.put(name, objectFactory.getObject());
			}

			return beans.get(name);
		}

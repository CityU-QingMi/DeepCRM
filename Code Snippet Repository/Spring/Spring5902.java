		@Override
		public List<Method> filter(List<Method> methods) {
			filterCalled = true;
			List<Method> forRemoval = new ArrayList<>();
			for (Method method: methods) {
				if (removeIfNotAnnotated && !isAnnotated(method)) {
					forRemoval.add(method);
				}
			}
			for (Method method: forRemoval) {
				methods.remove(method);
			}
			return methods;
		}

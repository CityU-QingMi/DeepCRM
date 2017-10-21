		@Override
		public int accept(Method method) {
			for (int i = 0; i < this.callbacks.length; i++) {
				if (!(this.callbacks[i] instanceof ConditionalCallback) ||
						((ConditionalCallback) this.callbacks[i]).isMatch(method)) {
					return i;
				}
			}
			throw new IllegalStateException("No callback available for method " + method.getName());
		}

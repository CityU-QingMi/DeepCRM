		public MethodAttributeAccess(String attributeName, Method method) {
			this.name = attributeName;
			try {
				method.setAccessible( true );
			}
			catch (Exception e) {
				this.method = null;
				return;
			}
			this.method = method;
		}

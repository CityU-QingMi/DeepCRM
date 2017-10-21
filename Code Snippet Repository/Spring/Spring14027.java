		@Override
		public InstanceHandle<Servlet> createInstance() throws InstantiationException {
			return new InstanceHandle<Servlet>() {
				@Override
				public Servlet getInstance() {
					return new DispatcherServlet(wac);
				}
				@Override
				public void release() {
				}
			};
		}

		@Override
		public InstanceHandle<Filter> createInstance() throws InstantiationException {
			return new InstanceHandle<Filter>() {
				@Override
				public Filter getInstance() {
					return filter;
				}
				@Override
				public void release() {}
			};
		}

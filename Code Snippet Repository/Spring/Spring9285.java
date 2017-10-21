		@Override
		public List<HttpMessageWriter<?>> getTypedWriters() {
			if (!this.shouldRegisterDefaults()) {
				return Collections.emptyList();
			}
			List<HttpMessageWriter<?>> result = super.getTypedWriters();
			result.add(new FormHttpMessageWriter());
			result.add(getMultipartHttpMessageWriter());
			return result;
		}

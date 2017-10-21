		@Override
		public List<HttpMessageReader<?>> getTypedReaders() {
			if (!shouldRegisterDefaults()) {
				return Collections.emptyList();
			}
			List<HttpMessageReader<?>> result = super.getTypedReaders();
			result.add(new FormHttpMessageReader());
			if (synchronossMultipartPresent) {
				SynchronossPartHttpMessageReader partReader = new SynchronossPartHttpMessageReader();
				result.add(partReader);
				result.add(new MultipartHttpMessageReader(partReader));
			}
			return result;
		}

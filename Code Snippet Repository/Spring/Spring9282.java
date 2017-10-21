		public List<HttpMessageWriter<?>> getObjectWriters() {
			if (!this.registerDefaults) {
				return Collections.emptyList();
			}
			List<HttpMessageWriter<?>> result = new ArrayList<>();
			if (jaxb2Present) {
				result.add(new EncoderHttpMessageWriter<>(new Jaxb2XmlEncoder()));
			}
			if (jackson2Present) {
				result.add(new EncoderHttpMessageWriter<>(jackson2JsonEncoder()));
			}
			if (jackson2SmilePresent) {
				result.add(new EncoderHttpMessageWriter<>(new Jackson2SmileEncoder()));
			}
			return result;
		}

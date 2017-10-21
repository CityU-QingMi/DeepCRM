		public List<HttpMessageReader<?>> getObjectReaders() {
			if (!this.registerDefaults) {
				return Collections.emptyList();
			}
			List<HttpMessageReader<?>> result = new ArrayList<>();
			if (jaxb2Present) {
				result.add(new DecoderHttpMessageReader<>(new Jaxb2XmlDecoder()));
			}
			if (jackson2Present) {
				result.add(new DecoderHttpMessageReader<>(jackson2JsonDecoder()));
			}
			if (jackson2SmilePresent) {
				result.add(new DecoderHttpMessageReader<>(new Jackson2SmileDecoder()));
			}
			return result;
		}

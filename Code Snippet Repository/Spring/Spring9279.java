		public List<HttpMessageReader<?>> getTypedReaders() {
			if (!this.registerDefaults) {
				return Collections.emptyList();
			}
			List<HttpMessageReader<?>> result = new ArrayList<>();
			result.add(new DecoderHttpMessageReader<>(new ByteArrayDecoder()));
			result.add(new DecoderHttpMessageReader<>(new ByteBufferDecoder()));
			result.add(new DecoderHttpMessageReader<>(new DataBufferDecoder()));
			result.add(new DecoderHttpMessageReader<>(new ResourceDecoder()));
			result.add(new DecoderHttpMessageReader<>(StringDecoder.textPlainOnly(splitTextOnNewLine())));
			return result;
		}

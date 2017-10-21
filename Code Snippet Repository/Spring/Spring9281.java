		public List<HttpMessageWriter<?>> getTypedWriters() {
			if (!this.registerDefaults) {
				return Collections.emptyList();
			}
			List<HttpMessageWriter<?>> result = new ArrayList<>();
			result.add(new EncoderHttpMessageWriter<>(new ByteArrayEncoder()));
			result.add(new EncoderHttpMessageWriter<>(new ByteBufferEncoder()));
			result.add(new EncoderHttpMessageWriter<>(new DataBufferEncoder()));
			result.add(new ResourceHttpMessageWriter());
			result.add(new EncoderHttpMessageWriter<>(CharSequenceEncoder.textPlainOnly()));
			return result;
		}

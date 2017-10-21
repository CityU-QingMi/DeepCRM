	@Override
	public Flux<TokenBuffer> apply(DataBuffer dataBuffer) {
		byte[] bytes = new byte[dataBuffer.readableByteCount()];
		dataBuffer.read(bytes);
		DataBufferUtils.release(dataBuffer);

		try {
			this.inputFeeder.feedInput(bytes, 0, bytes.length);
			List<TokenBuffer> result = new ArrayList<>();

			while (true) {
				JsonToken token = this.parser.nextToken();
				if (token == JsonToken.NOT_AVAILABLE) {
					break;
				}
				updateDepth(token);

				if (!this.tokenizeArrayElements) {
					processTokenNormal(token, result);
				}
				else {
					processTokenArray(token, result);
				}
			}
			return Flux.fromIterable(result);
		}
		catch (JsonProcessingException ex) {
			return Flux.error(new DecodingException(
					"JSON decoding error: " + ex.getOriginalMessage(), ex));
		}
		catch (Exception ex) {
			return Flux.error(ex);
		}
	}

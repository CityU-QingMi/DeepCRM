	@Before
	public void createContext() {
		final List<HttpMessageReader<?>> messageReaders = new ArrayList<>();
		messageReaders.add(new DecoderHttpMessageReader<>(new ByteBufferDecoder()));
		messageReaders.add(new DecoderHttpMessageReader<>(StringDecoder.allMimeTypes(true)));
		messageReaders.add(new DecoderHttpMessageReader<>(new Jaxb2XmlDecoder()));
		messageReaders.add(new DecoderHttpMessageReader<>(new Jackson2JsonDecoder()));
		messageReaders.add(new FormHttpMessageReader());
		SynchronossPartHttpMessageReader partReader = new SynchronossPartHttpMessageReader();
		messageReaders.add(partReader);
		messageReaders.add(new MultipartHttpMessageReader(partReader));

		messageReaders.add(new FormHttpMessageReader());

		this.context = new BodyExtractor.Context() {
			@Override
			public List<HttpMessageReader<?>> messageReaders() {
				return messageReaders;
			}

			@Override
			public Optional<ServerHttpResponse> serverResponse() {
				return Optional.empty();
			}

			@Override
			public Map<String, Object> hints() {
				return hints;
			}
		};
		this.hints = new HashMap<String, Object>();
	}

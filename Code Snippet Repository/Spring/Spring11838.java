	@Before
	public void setup() throws Exception {
		List<HttpMessageWriter<?>> writerList = new ArrayList<>(5);
		writerList.add(new EncoderHttpMessageWriter<>(new ByteBufferEncoder()));
		writerList.add(new EncoderHttpMessageWriter<>(CharSequenceEncoder.allMimeTypes()));
		writerList.add(new ResourceHttpMessageWriter());
		writerList.add(new EncoderHttpMessageWriter<>(new Jaxb2XmlEncoder()));
		writerList.add(new EncoderHttpMessageWriter<>(new Jackson2JsonEncoder()));
		RequestedContentTypeResolver resolver = new RequestedContentTypeResolverBuilder().build();
		this.resultHandler = new ResponseBodyResultHandler(writerList, resolver);
	}

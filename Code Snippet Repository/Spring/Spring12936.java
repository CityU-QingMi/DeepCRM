	@Test
	public void writeText() throws Exception {

		EmitterProcessor<String> processor = EmitterProcessor.create();
		ResponseBodyEmitter emitter = handleValue(processor, Flux.class, forClass(String.class));

		EmitterHandler emitterHandler = new EmitterHandler();
		emitter.initialize(emitterHandler);

		processor.onNext("The quick");
		processor.onNext(" brown fox jumps over ");
		processor.onNext("the lazy dog");
		processor.onComplete();

		assertEquals("The quick brown fox jumps over the lazy dog", emitterHandler.getValuesAsText());
	}

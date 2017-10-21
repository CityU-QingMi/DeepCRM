	@Nullable
	public ResponseBodyEmitter handleValue(Object returnValue, MethodParameter returnType,
			ModelAndViewContainer mav, NativeWebRequest request) throws Exception {

		Assert.notNull(returnValue, "Expected return value");
		ReactiveAdapter adapter = this.reactiveRegistry.getAdapter(returnValue.getClass());
		Assert.state(adapter != null, "Unexpected return value: " + returnValue);

		ResolvableType elementType = ResolvableType.forMethodParameter(returnType).getGeneric(0);
		Class<?> elementClass = elementType.resolve(Object.class);

		Collection<MediaType> mediaTypes = getMediaTypes(request);
		Optional<MediaType> mediaType = mediaTypes.stream().filter(MimeType::isConcrete).findFirst();

		if (adapter.isMultiValue()) {
			if (mediaTypes.stream().anyMatch(MediaType.TEXT_EVENT_STREAM::includes) ||
					ServerSentEvent.class.isAssignableFrom(elementClass)) {
				SseEmitter emitter = new SseEmitter(STREAMING_TIMEOUT_VALUE);
				new SseEmitterSubscriber(emitter, this.taskExecutor).connect(adapter, returnValue);
				return emitter;
			}
			if (CharSequence.class.isAssignableFrom(elementClass)) {
				ResponseBodyEmitter emitter = getEmitter(mediaType.orElse(MediaType.TEXT_PLAIN));
				new TextEmitterSubscriber(emitter, this.taskExecutor).connect(adapter, returnValue);
				return emitter;
			}
			if (mediaTypes.stream().anyMatch(MediaType.APPLICATION_STREAM_JSON::includes)) {
				ResponseBodyEmitter emitter = getEmitter(MediaType.APPLICATION_STREAM_JSON);
				new JsonEmitterSubscriber(emitter, this.taskExecutor).connect(adapter, returnValue);
				return emitter;
			}
		}

		// Not streaming...
		DeferredResult<Object> result = new DeferredResult<>();
		new DeferredResultSubscriber(result, adapter, elementType).connect(adapter, returnValue);
		WebAsyncUtils.getAsyncManager(request).startDeferredResultProcessing(result, mav);

		return null;
	}

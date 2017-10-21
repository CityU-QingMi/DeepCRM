	@Before
	public void setup() throws Exception {
		this.resolver = new HeadersMethodArgumentResolver();

		Method method = getClass().getDeclaredMethod("handleMessage", Map.class, String.class,
				MessageHeaders.class, MessageHeaderAccessor.class, TestMessageHeaderAccessor.class);

		this.paramAnnotated = new MethodParameter(method, 0);
		this.paramAnnotatedNotMap = new MethodParameter(method, 1);
		this.paramMessageHeaders = new MethodParameter(method, 2);
		this.paramMessageHeaderAccessor = new MethodParameter(method, 3);
		this.paramMessageHeaderAccessorSubclass = new MethodParameter(method, 4);

		Map<String, Object> headers = new HashMap<>();
		headers.put("foo", "bar");
		this.message = MessageBuilder.withPayload(new byte[0]).copyHeaders(headers).build();
	}

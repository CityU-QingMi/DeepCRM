	@Override
	@Nullable
	protected Object resolveArgumentInternal(MethodParameter parameter, Message<?> message, String name)
			throws Exception {

		@SuppressWarnings("unchecked")
		Map<String, String> vars =
				(Map<String, String>) message.getHeaders().get(DESTINATION_TEMPLATE_VARIABLES_HEADER);
		return (vars != null ? vars.get(name) : null);
	}

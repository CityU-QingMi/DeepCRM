		@SuppressWarnings("")
		@Override
		protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
				MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

			int status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
			response.setStatusCode(HttpStatus.OK);

			Map<String, Object> map = new LinkedHashMap<>();
			map.put("status", status);
			map.put("message", bodyContainer.getValue());
			bodyContainer.setValue(map);
		}

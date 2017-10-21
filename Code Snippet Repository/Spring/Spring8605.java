	@Test
	public void testFormData() throws Exception {
		String contentType = "application/x-www-form-urlencoded;charset=UTF-8";
		String body = "name+1=value+1&name+2=value+A&name+2=value+B&name+3";

		this.request.getHeaders().setContentType(MediaType.parseMediaType(contentType));
		this.request.getBody().write(body.getBytes(StandardCharsets.UTF_8));

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("name 1", "value 1");
		map.add("name 2", "value A");
		map.add("name 2", "value B");
		map.add("name 3", null);
		MockRestRequestMatchers.content().formData(map).match(this.request);
	}

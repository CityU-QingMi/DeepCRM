	@Test
	public void testMultipart() throws Exception {

		MultipartBean bean = new MultipartBean();
		WebExchangeDataBinder binder = new WebExchangeDataBinder(bean);

		MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
		data.add("name", "bar");
		data.add("someList", "123");
		data.add("someList", "abc");
		data.add("someArray", "dec");
		data.add("someArray", "456");
		data.add("part", new ClassPathResource("org/springframework/http/codec/multipart/foo.txt"));
		data.add("somePartList", new ClassPathResource("org/springframework/http/codec/multipart/foo.txt"));
		data.add("somePartList", new ClassPathResource("org/springframework/http/server/reactive/spring.png"));
		binder.bind(exchangeMultipart(data)).block(Duration.ofMillis(5000));

		assertEquals("bar", bean.getName());
		assertEquals(Arrays.asList("123", "abc"), bean.getSomeList());
		assertArrayEquals(new String[] {"dec", "456"}, bean.getSomeArray());
		assertEquals("foo.txt", bean.getPart().filename());
		assertEquals(2, bean.getSomePartList().size());
		assertEquals("foo.txt", bean.getSomePartList().get(0).filename());
		assertEquals("spring.png", bean.getSomePartList().get(1).filename());
	}

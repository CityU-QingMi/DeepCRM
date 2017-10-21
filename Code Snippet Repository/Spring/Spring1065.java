	@Test
	public void testUntypedPropertyWithMapAtRuntime() {
		class Holder<D> {
			private final D data;
			public Holder(D data) {
				this.data = data;
			}
			public D getData() {
				return this.data;
			}
		}

		Map<String, Object> data = new HashMap<>();
		data.put("x", "y");
		Holder<Map<String, Object>> context = new Holder<>(data);

		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(context);
		assertEquals("y", bw.getPropertyValue("data['x']"));

		bw.setPropertyValue("data['message']", "it works!");
		assertEquals(data.get("message"), "it works!");
	}

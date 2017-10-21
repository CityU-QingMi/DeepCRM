	@Test
	public void putAll() {
		Map<String, Object> map = new HashMap<>();
		map.put("person", new TestBean("Fred"));
		map.put("age", 33);
		this.redirectAttributes.putAll(map);

		assertEquals("Fred", this.redirectAttributes.get("person"));
		assertEquals("33", this.redirectAttributes.get("age"));
	}

	@Test
	public void addAttributesMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("person", new TestBean("Fred"));
		map.put("age", 33);
		this.redirectAttributes.addAllAttributes(map);

		assertEquals("Fred", this.redirectAttributes.get("person"));
		assertEquals("33", this.redirectAttributes.get("age"));
	}

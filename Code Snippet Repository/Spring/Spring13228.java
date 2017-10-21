	@Test
	public void mergeAttributes() {
		Map<String, Object> map = new HashMap<>();
		map.put("person", new TestBean("Fred"));
		map.put("age", 33);

		this.redirectAttributes.addAttribute("person", new TestBean("Ralph"));
		this.redirectAttributes.mergeAttributes(map);

		assertEquals("Ralph", this.redirectAttributes.get("person"));
		assertEquals("33", this.redirectAttributes.get("age"));
	}

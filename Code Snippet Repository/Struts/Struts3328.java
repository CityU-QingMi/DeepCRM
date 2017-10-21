	private Map getData() {

		Map data = new HashMap();
		data.put("a", "x");
		data.put("b", "y");

		List list = new ArrayList();
		list.add("p");
		list.add("q");
		list.add("r");
		data.put("c", list);

		Map map = new HashMap();
		map.put("x", "a");
		map.put("y", "b");
		data.put("d", map);

		return data;

	}

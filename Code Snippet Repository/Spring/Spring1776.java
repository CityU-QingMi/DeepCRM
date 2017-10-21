	public void populate() {
		TestBean tb0 = new TestBean("name0", 0);
		TestBean tb1 = new TestBean("name1", 0);
		TestBean tb2 = new TestBean("name2", 0);
		TestBean tb3 = new TestBean("name3", 0);
		TestBean tb4 = new TestBean("name4", 0);
		TestBean tb5 = new TestBean("name5", 0);
		TestBean tb6 = new TestBean("name6", 0);
		TestBean tb7 = new TestBean("name7", 0);
		TestBean tbX = new TestBean("nameX", 0);
		TestBean tbY = new TestBean("nameY", 0);
		this.array = new TestBean[] {tb0, tb1};
		this.list = new ArrayList<>();
		this.list.add(tb2);
		this.list.add(tb3);
		this.set = new TreeSet<>();
		this.set.add(tb6);
		this.set.add(tb7);
		this.map = new HashMap<>();
		this.map.put("key1", tb4);
		this.map.put("key2", tb5);
		this.map.put("key.3", tb5);
		List list = new ArrayList();
		list.add(tbX);
		list.add(tbY);
		this.map.put("key4", list);
	}

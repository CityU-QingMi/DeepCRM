	public Category(long id, String name, Category... children) {
		this.id = id;
		this.name = name;
		this.children = new ArrayList<Category>();
		for (Category child : children) {
			this.children.add(child);
		}

		catMap.put(id, this);
	}

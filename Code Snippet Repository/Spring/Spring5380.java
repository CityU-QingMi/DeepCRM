	private void doTestWithElementFactory(AutoPopulatingList<Object> list) {
		doTestWithClass(list);

		for (int x = 0; x < list.size(); x++) {
			Object element = list.get(x);
			if (element instanceof TestObject) {
				assertEquals(x, ((TestObject) element).getAge());
			}
		}
	}

	@Test
	public void testJavaBeanPropertyConventions() {
		Book book = new Book();
		DataBinder binder = new DataBinder(book);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("title", "my book");
		pvs.add("ISBN", "1234");
		pvs.add("NInStock", "5");
		binder.bind(pvs);
		assertEquals("my book", book.getTitle());
		assertEquals("1234", book.getISBN());
		assertEquals(5, book.getNInStock());

		pvs = new MutablePropertyValues();
		pvs.add("Title", "my other book");
		pvs.add("iSBN", "6789");
		pvs.add("nInStock", "0");
		binder.bind(pvs);
		assertEquals("my other book", book.getTitle());
		assertEquals("6789", book.getISBN());
		assertEquals(0, book.getNInStock());
	}

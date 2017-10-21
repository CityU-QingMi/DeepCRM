	@Test
	public void extractFileExtension() {
		assertEquals("html", UriUtils.extractFileExtension("index.html"));
		assertEquals("html", UriUtils.extractFileExtension("/index.html"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html#/a"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html#/path/a"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html#/path/a.do"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html#aaa?bbb"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html#aaa.xml?bbb"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html?param=a"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html?param=/path/a"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html?param=/path/a.do"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html?param=/path/a#/path/a"));
		assertEquals("html", UriUtils.extractFileExtension("/products/view.html?param=/path/a.do#/path/a.do"));
		assertEquals("html", UriUtils.extractFileExtension("/products;q=11/view.html?param=/path/a.do"));
		assertEquals("html", UriUtils.extractFileExtension("/products;q=11/view.html;r=22?param=/path/a.do"));
		assertEquals("html", UriUtils.extractFileExtension("/products;q=11/view.html;r=22;s=33?param=/path/a.do"));
	}

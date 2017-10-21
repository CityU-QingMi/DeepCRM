	@Test
	public void testHistoryOfPage() {
		assert getAuditReader().find( WikiPage.class, pageId, 1 ).getImages().size() == 0;
		assert getAuditReader().find( WikiPage.class, pageId, 2 ).getImages().equals(
				TestTools.makeSet(
						new WikiImage(
								"name1"
						)
				)
		);
		assert getAuditReader().find( WikiPage.class, pageId, 3 ).getImages().equals(
				TestTools.makeSet(
						new WikiImage(
								"name2"
						)
				)
		);
	}

	@Test
	public void combine() {
		TestPathCombiner pathMatcher = new TestPathCombiner();
		assertEquals("", pathMatcher.combine("", ""));
		assertEquals("/hotels", pathMatcher.combine("/hotels", ""));
		assertEquals("/hotels", pathMatcher.combine("", "/hotels"));
		assertEquals("/hotels/booking", pathMatcher.combine("/hotels/*", "booking"));
		assertEquals("/hotels/booking", pathMatcher.combine("/hotels/*", "/booking"));
		assertEquals("/hotels/**/booking", pathMatcher.combine("/hotels/**", "booking"));
		assertEquals("/hotels/**/booking", pathMatcher.combine("/hotels/**", "/booking"));
		assertEquals("/hotels/booking", pathMatcher.combine("/hotels", "/booking"));
		assertEquals("/hotels/booking", pathMatcher.combine("/hotels", "booking"));
		assertEquals("/hotels/booking", pathMatcher.combine("/hotels/", "booking"));
		assertEquals("/hotels/{hotel}", pathMatcher.combine("/hotels/*", "{hotel}"));
		assertEquals("/hotels/**/{hotel}", pathMatcher.combine("/hotels/**", "{hotel}"));
		assertEquals("/hotels/{hotel}", pathMatcher.combine("/hotels", "{hotel}"));
		assertEquals("/hotels/{hotel}.*", pathMatcher.combine("/hotels", "{hotel}.*"));
		assertEquals("/hotels/*/booking/{booking}",
				pathMatcher.combine("/hotels/*/booking", "{booking}"));
		assertEquals("/hotel.html", pathMatcher.combine("/*.html", "/hotel.html"));
		assertEquals("/hotel.html", pathMatcher.combine("/*.html", "/hotel"));
		assertEquals("/hotel.html", pathMatcher.combine("/*.html", "/hotel.*"));
		// TODO this seems rather bogus, should we eagerly show an error?
		assertEquals("/d/e/f/hotel.html", pathMatcher.combine("/a/b/c/*.html", "/d/e/f/hotel.*"));
		assertEquals("/*.html", pathMatcher.combine("/**", "/*.html"));
		assertEquals("/*.html", pathMatcher.combine("/*", "/*.html"));
		assertEquals("/*.html", pathMatcher.combine("/*.*", "/*.html"));
		assertEquals("/{foo}/bar", pathMatcher.combine("/{foo}", "/bar"));  // SPR-8858
		assertEquals("/user/user", pathMatcher.combine("/user", "/user"));  // SPR-7970
		assertEquals("/{foo:.*[^0-9].*}/edit/",
				pathMatcher.combine("/{foo:.*[^0-9].*}", "/edit/"));  // SPR-10062
		assertEquals("/1.0/foo/test", pathMatcher.combine("/1.0", "/foo/test"));
		// SPR-10554
		assertEquals("/hotel", pathMatcher.combine("/", "/hotel"));  // SPR-12975
		assertEquals("/hotel/booking", pathMatcher.combine("/hotel/", "/booking"));  // SPR-12975
		assertEquals("/hotel", pathMatcher.combine("", "/hotel"));
		assertEquals("/hotel", pathMatcher.combine("/hotel", ""));
		// TODO Do we need special handling when patterns contain multiple dots?
	}

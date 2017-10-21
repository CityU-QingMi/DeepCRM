	@Test
	public void isSameOrigin() {
		assertTrue(checkSameOrigin("mydomain1.com", -1, "http://mydomain1.com"));
		assertTrue(checkSameOrigin("mydomain1.com", -1, "http://mydomain1.com:80"));
		assertTrue(checkSameOrigin("mydomain1.com", 443, "https://mydomain1.com"));
		assertTrue(checkSameOrigin("mydomain1.com", 443, "https://mydomain1.com:443"));
		assertTrue(checkSameOrigin("mydomain1.com", 123, "http://mydomain1.com:123"));
		assertTrue(checkSameOrigin("mydomain1.com", -1, "ws://mydomain1.com"));
		assertTrue(checkSameOrigin("mydomain1.com", 443, "wss://mydomain1.com"));

		assertFalse(checkSameOrigin("mydomain1.com", -1, "http://mydomain2.com"));
		assertFalse(checkSameOrigin("mydomain1.com", -1, "https://mydomain1.com"));
		assertFalse(checkSameOrigin("mydomain1.com", -1, "invalid-origin"));

		// Handling of invalid origins as described in SPR-13478
		assertTrue(checkSameOrigin("mydomain1.com", -1, "http://mydomain1.com/"));
		assertTrue(checkSameOrigin("mydomain1.com", -1, "http://mydomain1.com:80/"));
		assertTrue(checkSameOrigin("mydomain1.com", -1, "http://mydomain1.com/path"));
		assertTrue(checkSameOrigin("mydomain1.com", -1, "http://mydomain1.com:80/path"));
		assertFalse(checkSameOrigin("mydomain2.com", -1, "http://mydomain1.com/"));
		assertFalse(checkSameOrigin("mydomain2.com", -1, "http://mydomain1.com:80/"));
		assertFalse(checkSameOrigin("mydomain2.com", -1, "http://mydomain1.com/path"));
		assertFalse(checkSameOrigin("mydomain2.com", -1, "http://mydomain1.com:80/path"));

		// Handling of IPv6 hosts as described in SPR-13525
		assertTrue(checkSameOrigin("[::1]", -1, "http://[::1]"));
		assertTrue(checkSameOrigin("[::1]", 8080, "http://[::1]:8080"));
		assertTrue(checkSameOrigin("[2001:0db8:0000:85a3:0000:0000:ac1f:8001]", -1, "http://[2001:0db8:0000:85a3:0000:0000:ac1f:8001]"));
		assertTrue(checkSameOrigin("[2001:0db8:0000:85a3:0000:0000:ac1f:8001]", 8080, "http://[2001:0db8:0000:85a3:0000:0000:ac1f:8001]:8080"));
		assertFalse(checkSameOrigin("[::1]", -1, "http://[::1]:8080"));
		assertFalse(checkSameOrigin("[::1]", 8080, "http://[2001:0db8:0000:85a3:0000:0000:ac1f:8001]:8080"));
	}

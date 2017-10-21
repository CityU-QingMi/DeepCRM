	@Test
	public void testArraysSameLength() {
		assertEquals(
				0,
				RowVersionComparator.INSTANCE.compare(
						new byte[] {},
						new byte[] {}
				)
		);
		assertEquals(
				0,
				RowVersionComparator.INSTANCE.compare(
						new byte[] { 1 },
						new byte[] { 1 }
				)
		);
		assertEquals(
				0,
				RowVersionComparator.INSTANCE.compare(
						new byte[] { 1, 2 },
						new byte[] { 1, 2 }
				)
		);
		assertTrue(
				RowVersionComparator.INSTANCE.compare(
						new byte[] { 0, 2 },
						new byte[] { 1, 2 }
				) < 0
		);

		assertTrue(
				RowVersionComparator.INSTANCE.compare(
						new byte[] { 1, 1 },
						new byte[] { 1, 2 }
				) < 0
		);

		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 2, 2 },
						new byte[] { 1, 2 }
				) > 0
		);

		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 2, 2 },
						new byte[] { 2, 1 }
				) > 0
		);
	}

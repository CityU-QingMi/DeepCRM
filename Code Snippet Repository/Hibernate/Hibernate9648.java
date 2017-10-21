	@Test
	public void testArraysDifferentLength() {
		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] {},
						new byte[] { 1 }
				) < 0
		);

		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 1 },
						new byte[] {}
				) > 0
		);

		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 1 },
						new byte[] { 1, 2 }
				) < 0
		);
		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 1, 2 },
						new byte[] { 1 }
				) > 0
		);

		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 2 },
						new byte[] { 1, 2 }
				) > 0
		);
		assertTrue( RowVersionComparator.INSTANCE.compare(
						new byte[] { 1, 2 },
						new byte[] { 2 }
				) < 0
		);

	}

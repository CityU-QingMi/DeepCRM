    private void assertAllIdsPresent(ResultSet rs, int... expectedIds) throws SQLException {
		Set<Integer> expected = new TreeSet<Integer>();
		Set<Integer> found = new TreeSet<Integer>();
		if(expectedIds != null) {
			for(int id : expectedIds) {
				expected.add(id);
			}
		}
		while(rs.next()) {
			found.add(rs.getInt(1));
		}
		Assert.assertEquals(expected.size(), found.size());
		Assert.assertTrue(found.containsAll(expected));
	}

	@Test
	public void testIterator() {
		l.add( action1 );
		l.add( action2 );
		l.add( action3 );
		Iterator<AnExecutable> iterator = l.iterator();
		Assert.assertEquals(action1, iterator.next());
		Assert.assertEquals(action2, iterator.next());
		Assert.assertEquals(action3, iterator.next());
		Assert.assertFalse(iterator.hasNext());
	}

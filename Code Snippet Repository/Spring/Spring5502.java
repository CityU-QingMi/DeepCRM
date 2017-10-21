	@Test
	public void declaresException() throws Exception {
		Method remoteExMethod = A.class.getDeclaredMethod("foo", Integer.class);
		assertTrue(ReflectionUtils.declaresException(remoteExMethod, RemoteException.class));
		assertTrue(ReflectionUtils.declaresException(remoteExMethod, ConnectException.class));
		assertFalse(ReflectionUtils.declaresException(remoteExMethod, NoSuchMethodException.class));
		assertFalse(ReflectionUtils.declaresException(remoteExMethod, Exception.class));

		Method illegalExMethod = B.class.getDeclaredMethod("bar", String.class);
		assertTrue(ReflectionUtils.declaresException(illegalExMethod, IllegalArgumentException.class));
		assertTrue(ReflectionUtils.declaresException(illegalExMethod, NumberFormatException.class));
		assertFalse(ReflectionUtils.declaresException(illegalExMethod, IllegalStateException.class));
		assertFalse(ReflectionUtils.declaresException(illegalExMethod, Exception.class));
	}

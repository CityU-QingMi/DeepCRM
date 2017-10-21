	@Test
	public void testListeners() throws Exception {
		TestBean testBean1 = context.getBean("testBean1", TestBean.class);
		TestBean testBean2 = context.getBean("testBean2", TestBean.class);
		TestMessageListener testBean3 = context.getBean("testBean3", TestMessageListener.class);

		assertNull(testBean1.getName());
		assertNull(testBean2.getName());
		assertNull(testBean3.message);

		TextMessage message1 = mock(TextMessage.class);
		given(message1.getText()).willReturn("Test1");

		MessageListener listener1 = getListener("listener1");
		listener1.onMessage(message1);
		assertEquals("Test1", testBean1.getName());

		TextMessage message2 = mock(TextMessage.class);
		given(message2.getText()).willReturn("Test2");

		MessageListener listener2 = getListener("listener2");
		listener2.onMessage(message2);
		assertEquals("Test2", testBean2.getName());

		TextMessage message3 = mock(TextMessage.class);

		MessageListener listener3 = getListener(DefaultMessageListenerContainer.class.getName() + "#0");
		listener3.onMessage(message3);
		assertSame(message3, testBean3.message);
	}

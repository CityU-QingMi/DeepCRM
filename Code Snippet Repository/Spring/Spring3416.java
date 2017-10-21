	@Test
	public void metaAnnotationIsDiscovered() {
		load(MetaAnnotationListenerTestBean.class);
		MetaAnnotationListenerTestBean bean = this.context.getBean(MetaAnnotationListenerTestBean.class);
		this.eventCollector.assertNoEventReceived(bean);

		TestEvent event = new TestEvent();
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(bean, event);
		this.eventCollector.assertTotalEventsCount(1);
	}

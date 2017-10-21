	@Test
	public void eventsWithNoSource() throws Exception {
		// See SPR-10945 Serialized events result in a null source
		MyEvent event = new MyEvent(this);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(event);
		oos.close();
		event = (MyEvent) new ObjectInputStream(new ByteArrayInputStream(
				bos.toByteArray())).readObject();
		doTestEvents(this.listener, this.parentListener, event);
	}

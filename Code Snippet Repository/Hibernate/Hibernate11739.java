	@Override
	public Object down(Event evt) {
		switch (evt.getType()) {
			case Event.SET_LOCAL_ADDRESS:
				localAddress = (Address) evt.getArg();
				log.trace("Set address " + localAddress);
				break;
			case Event.CONNECT:
			case Event.CONNECT_WITH_STATE_TRANSFER:
			case Event.CONNECT_USE_FLUSH:
			case Event.CONNECT_WITH_STATE_TRANSFER_USE_FLUSH:
				log.trace("Connecting on " + localAddress);
				// we need to pass the message from below GMS (let's say regular FD* protocols
				connected.add(getFD());
				break;
			case Event.DISCONNECT:
				log.trace("Disconnecting on " + localAddress);
				connected.remove(getFD());
				// reduce view ack collection timeout to minimum, since we don't want to wait anymore
				GMS gms = (GMS) getProtocolStack().findProtocol(GMS.class);
				gms.setViewAckCollectionTimeout(1);
				for (Protocol other : connected) {
					executor.execute(() -> {
						log.trace("Suspecting " + localAddress + " on " + other);
						Event suspectEvent = new Event(Event.SUSPECT, localAddress);
						other.up(suspectEvent);
						other.down(suspectEvent);
					});
				}
				break;
		}
		return super.down(evt);
	}

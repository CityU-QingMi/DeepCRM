	protected void applyAcknowledgeMode(BeanWrapper bw, int ackMode) {
		if (ackMode == Session.SESSION_TRANSACTED) {
			throw new IllegalArgumentException("No support for SESSION_TRANSACTED: Only \"Auto-acknowledge\" " +
					"and \"Dups-ok-acknowledge\" supported in standard JCA 1.5");
		}
		else if (ackMode == Session.CLIENT_ACKNOWLEDGE) {
			throw new IllegalArgumentException("No support for CLIENT_ACKNOWLEDGE: Only \"Auto-acknowledge\" " +
					"and \"Dups-ok-acknowledge\" supported in standard JCA 1.5");
		}
		else if (bw.isWritableProperty("acknowledgeMode")) {
			bw.setPropertyValue("acknowledgeMode",
					ackMode == Session.DUPS_OK_ACKNOWLEDGE ? "Dups-ok-acknowledge" : "Auto-acknowledge");
		}
		else if (ackMode == Session.DUPS_OK_ACKNOWLEDGE) {
			// Standard JCA 1.5 "acknowledgeMode" apparently not supported (e.g. WebSphere MQ 6.0.2.1)
			throw new IllegalArgumentException("Dups-ok-acknowledge not supported by underlying provider");
		}
	}

		@Override
		public void sendMessage(Message message, Address[] addresses) throws MessagingException {
			if ("fail".equals(message.getSubject())) {
				throw new MessagingException("failed");
			}
			if (!ObjectUtils.nullSafeEquals(addresses, message.getAllRecipients())) {
				throw new MessagingException("addresses not correct");
			}
			if (message.getSentDate() == null) {
				throw new MessagingException("No sentDate specified");
			}
			if (message.getSubject() != null && message.getSubject().contains("custom")) {
				assertEquals(new GregorianCalendar(2005, 3, 1).getTime(), message.getSentDate());
			}
			this.sentMessages.add(message);
		}

	public static String buildExceptionMessage(JMSException ex) {
		String message = ex.getMessage();
		Exception linkedEx = ex.getLinkedException();
		if (linkedEx != null) {
			if (message == null) {
				message = linkedEx.toString();
			}
			else {
				String linkedMessage = linkedEx.getMessage();
				if (linkedMessage != null && !message.contains(linkedMessage)) {
					message = message + "; nested exception is " + linkedEx;
				}
			}
		}
		return message;
	}

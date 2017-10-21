		@Override
		public void handleNotification(Notification notification, Object handback) {
			if (notification instanceof AttributeChangeNotification) {
				AttributeChangeNotification attNotification = (AttributeChangeNotification) notification;
				String attributeName = attNotification.getAttributeName();

				Integer currentCount = (Integer) this.attributeCounts.get(attributeName);

				if (currentCount != null) {
					int count = currentCount.intValue() + 1;
					this.attributeCounts.put(attributeName, new Integer(count));
				}
				else {
					this.attributeCounts.put(attributeName, new Integer(1));
				}

				this.attributeHandbacks.put(attributeName, handback);
			}
		}

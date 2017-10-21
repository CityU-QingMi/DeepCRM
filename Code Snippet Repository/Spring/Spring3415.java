		@EventListener
		public Object handle(AnotherTestEvent event) {
			collectEvent(event);
			if (event.content == null) {
				return null;
			}
			else if (event.content instanceof String) {
				String s = (String) event.content;
				if (s.equals("String")) {
					return event.content;
				}
				else {
					return new TestEvent(this, event.getId(), s);
				}
			}
			return event.content;
		}

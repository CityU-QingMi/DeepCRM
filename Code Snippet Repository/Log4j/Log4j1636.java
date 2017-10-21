		private void trigger() {
			Holder.LOGGER.info("Attempt to trigger");
			assertTrue("Logger is of type " + Holder.LOGGER.getClass().getName(), Holder.LOGGER instanceof TestLogger);
			if (((TestLogger) Holder.LOGGER).getEntries().size() == 0) {
				System.out.println("Logger contains no messages");
			}
			for (final String msg : ((TestLogger) Holder.LOGGER).getEntries()) {
				System.out.println(msg);
			}
		}

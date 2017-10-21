        @Override
        public void run() {

            for (int i = start; i < stop; ++i) {
                Event event = primary.poll();
                while (event == null) {
                    event = primary.poll();
                }

                Assert.assertNotNull("Received " + i + " events. Event "
                    + (i + 1) + " is null", event);
                final String value = event.getHeaders().get("counter");
                Assert.assertNotNull("Missing counter", value);
                final int counter = Integer.parseInt(value);
                if (fields[counter]) {
                    Assert.fail("Duplicate event");
                } else {
                    fields[counter] = true;
                }

            }
        }

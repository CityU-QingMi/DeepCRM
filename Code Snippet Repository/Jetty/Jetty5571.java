        public void assertEvents(Map<String, PathWatchEventType[]> expectedEvents)
        {
            try
            {
                assertThat("Event match (file|directory) count", this.events.size(), is(expectedEvents.size()));

                for (Map.Entry<String, PathWatchEventType[]> entry : expectedEvents.entrySet())
                {
                    String relativePath = entry.getKey();
                    PathWatchEventType[] expectedTypes = entry.getValue();
                    assertEvents(relativePath, expectedTypes);
                }
            }
            catch(Throwable th)
            {
                System.err.println(this.events);
                throw th;
            }
        }

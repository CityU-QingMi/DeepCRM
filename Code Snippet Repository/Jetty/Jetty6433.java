        public void assertReceivedCloseEvent(int clientTimeoutMs, Matcher<Integer> statusCodeMatcher, Matcher<String> reasonMatcher)
                throws InterruptedException
        {
            long maxTimeout = clientTimeoutMs * 4;

            assertThat("Client Close Event Occurred",closeLatch.await(maxTimeout,TimeUnit.MILLISECONDS),is(true));
            assertThat("Client Close Event Count",closeCount.get(),is(1));
            assertThat("Client Close Event Status Code",closeCode,statusCodeMatcher);
            if (reasonMatcher == null)
            {
                assertThat("Client Close Event Reason",closeReason,nullValue());
            }
            else
            {
                assertThat("Client Close Event Reason",closeReason,reasonMatcher);
            }
        }

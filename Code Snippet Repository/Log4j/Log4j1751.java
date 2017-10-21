    protected void checkTheEqualityOfSentAndReceivedMessages(final Level expectedLevel) throws InterruptedException {
        final List<String> receivedMessages = getReceivedMessages(DEFAULT_TIMEOUT_IN_MS);

        assertNotNull("No messages received", receivedMessages);
        for (int i = 0; i < receivedMessages.size(); i++) {
            final String receivedMessage = receivedMessages.get(i);
            final String sentMessage = sentMessages.get(i);
            final String suffix = includeNewLine ? "\n" : Strings.EMPTY;
            assertTrue("Incorrect message received: " + receivedMessage,
                    receivedMessage.endsWith(sentMessage + suffix) || receivedMessage.contains(sentMessage));
            final int expectedPriority = Priority.getPriority(getExpectedFacility(), expectedLevel);
            assertTrue("Expected facility " + expectedPriority + " in message " + receivedMessage,
                    receivedMessage.startsWith("<" + expectedPriority + ">"));
        }
    }

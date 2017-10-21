    @Override
    public List<String> call() throws Exception {
        try (ZMQ.Socket subscriber = context.socket(ZMQ.SUB)) {
            subscriber.connect(endpoint);
            subscriber.subscribe(new byte[0]);
            for (int messageNum = 0; messageNum < receiveCount
                    && !Thread.currentThread().isInterrupted(); messageNum++) {
                // Use trim to remove the tailing '0' character
                messages.add(subscriber.recvStr(0).trim());
            }
        }
        return messages;
    }

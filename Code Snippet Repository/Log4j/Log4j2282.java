    private synchronized void processFrames() throws IOException {
        try {
            int count = 0;
            while (true) {
                String message = Strings.EMPTY;
                message = syslogReader.read();
                messageList.add(message);
                count++;
                if (isEndOfMessages(count)) {
                    break;
                }
            }
            this.notify();
        }
        catch(final Exception e) {
            this.notify();
            throw new IOException(e);
        }
        return;
    }

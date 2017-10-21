    public final synchronized void write(final LogEvent event) {
        if (this.bufferSize > 0) {
            this.buffer.add(event);
            if (this.buffer.size() >= this.bufferSize || event.isEndOfBatch()) {
                this.flush();
            }
        } else {
            this.connectAndStart();
            try {
                this.writeInternal(event);
            } finally {
                this.commitAndClose();
            }
        }
    }

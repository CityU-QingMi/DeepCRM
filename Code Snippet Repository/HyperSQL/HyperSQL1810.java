    void setWriteDelay(int delay) {

        writeDelay = delay;

        if (dbLogWriter != null && dbLogWriter.getWriteDelay() != delay) {
            dbLogWriter.forceSync();
            dbLogWriter.stop();
            dbLogWriter.setWriteDelay(delay);
            dbLogWriter.start();
        }
    }

    public synchronized void setWriteDelay(int delay) {

        propWriteDelay = delay;

        if (log != null) {
            syncFile = (delay == 0);

            log.setWriteDelay(delay);
        }
    }

    synchronized TriggerData popPair() {

        if (rowsQueued == 0) {
            try {
                wait();    // this releases the lock monitor
            } catch (InterruptedException e) {

                /* ignore and resume */
            }
        }

        rowsQueued--;

        notify();    // notify push's wait

        if (pendingQueue.size() == 0) {
            return null;
        } else {
            return (TriggerData) pendingQueue.removeFirst();
        }
    }

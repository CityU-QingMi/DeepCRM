        void cancel() {

            boolean signalCancelled = false;

            synchronized (cancel_mutex) {
                if (!cancelled) {
                    cancelled = signalCancelled = true;
                }
            }

            if (signalCancelled) {
                HsqlTimer.this.taskQueue.signalTaskCancelled(this);
            }
        }

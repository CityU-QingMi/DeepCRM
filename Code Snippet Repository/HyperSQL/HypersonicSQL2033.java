        synchronized Object setPeriod(final long newPeriod) {

            if (this.period == newPeriod || this.isCancelled()) {
                return this;
            } else if (newPeriod > this.period) {
                this.period = newPeriod;

                return this;
            } else {
                this.cancel();

                return HsqlTimer.this.addTask(now(), this.runnable, newPeriod,
                                              this.relative);
            }
        }

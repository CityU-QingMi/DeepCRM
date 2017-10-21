    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timer)) {
            return false;
        }

        final Timer timer = (Timer) o;

        if (elapsedTime != timer.elapsedTime) {
            return false;
        }
        if (startTime != timer.startTime) {
            return false;
        }
        if (name != null ? !name.equals(timer.name) : timer.name != null) {
            return false;
        }
        if (status != null ? !status.equals(timer.status) : timer.status != null) {
            return false;
        }

        return true;
    }

    private void resetCurrentTimestamp() {

        if (currentTimestampSCN != actionTimestamp) {
            currentTimestampSCN = actionTimestamp;
            currentMillis       = System.currentTimeMillis();
            currentDate         = null;
            currentTimestamp    = null;
            localTimestamp      = null;
            currentTime         = null;
            localTime           = null;
        }
    }

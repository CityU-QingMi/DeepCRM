    public void setSampledStatisticsEnabled(boolean enabled) {
        if (enabled != this.sampledStatisticsEnabled) {
            this.sampledStatisticsEnabled = enabled;
            if(enabled) {
                this.sampledStatistics = new SampledStatisticsImpl(scheduler);
            }
            else {
                 this.sampledStatistics.shutdown(); 
                 this.sampledStatistics = NULL_SAMPLED_STATISTICS;
            }
            sendNotification(SAMPLED_STATISTICS_ENABLED, Boolean.valueOf(enabled));
        }
    }

    public void setUpdateQuietTime(long duration, TimeUnit unit)
    {
        long desiredMillis = unit.toMillis(duration);
        
        if (watchService != null && !this.nativeWatchService && (desiredMillis < 5000))
        {
            LOG.warn("Quiet Time is too low for non-native WatchService [{}]: {} < 5000 ms (defaulting to 5000 ms)",watchService.getClass().getName(),desiredMillis);
            this.updateQuietTimeDuration = 5000;
            this.updateQuietTimeUnit = TimeUnit.MILLISECONDS;
            return;
        }

        if (IS_WINDOWS && (desiredMillis < 1000))
        {
            LOG.warn("Quiet Time is too low for Microsoft Windows: {} < 1000 ms (defaulting to 1000 ms)",desiredMillis);
            this.updateQuietTimeDuration = 1000;
            this.updateQuietTimeUnit = TimeUnit.MILLISECONDS;
            return;
        }
        
        // All other OS and watch service combinations can use desired setting
        this.updateQuietTimeDuration = duration;
        this.updateQuietTimeUnit = unit;
    }

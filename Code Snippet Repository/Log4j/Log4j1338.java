    public Integer getActualTrafficClass() {
        if (trafficClass != null && rfc1349TrafficClass != null) {
            throw new IllegalStateException("You MUST not set both customTrafficClass and trafficClass.");
        }
        if (trafficClass != null) {
            return trafficClass;
        }
        if (rfc1349TrafficClass != null) {
            return Integer.valueOf(rfc1349TrafficClass.value());
        }
        return null;
    }

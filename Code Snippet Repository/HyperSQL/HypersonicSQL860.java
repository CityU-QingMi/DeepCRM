    void setFeature(String feature, boolean value) {

        int number = 8;

        if (value) {
            sessionOptimization |= number;
        } else {
            sessionOptimization &= ~number;
        }
    }

    private Double getVariance() {

        if (!initialized) {
            return null;
        }

        return sample ? (n == 1) ? null    // NULL (not NaN) is correct in this case
                                 : new Double(vk / (double) (n - 1))
                      : new Double(vk / (double) (n));
    }

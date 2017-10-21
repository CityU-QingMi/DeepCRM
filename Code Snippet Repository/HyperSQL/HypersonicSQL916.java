    private Double getStdDev() {

        if (!initialized) {
            return null;
        }

        return sample ? (n == 1) ? null    // NULL (not NaN) is correct in this case
                                 : new Double(Math.sqrt(vk / (double) (n - 1)))
                      : new Double(Math.sqrt(vk / (double) (n)));
    }

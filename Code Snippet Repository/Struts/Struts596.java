    private void setRoundingMode(NumberFormat format) {
        if (roundingMode != null) {
            roundingMode = findString(roundingMode);
            if ("ceiling".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.CEILING);
            } else if ("down".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.DOWN);
            } else if ("floor".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.FLOOR);
            } else if ("half-down".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.HALF_DOWN);
            } else if ("half-even".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.HALF_EVEN);
            } else if ("half-up".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.HALF_UP);
            } else if ("unnecessary".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.UNNECESSARY);
            } else if ("up".equals(roundingMode)) {
                format.setRoundingMode(RoundingMode.UP);
            } else {
                LOG.error("Could not recognise a roundingMode of [" + roundingMode + "]");
            }
        }
    }

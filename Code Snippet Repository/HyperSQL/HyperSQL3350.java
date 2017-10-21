    private boolean isHidingLines() {
        switch(state) {
            case CONDITION_ARMED :
            case CONDITION_TRIGGERED: {
                return true;
            }
            default : {
                return false;
            }
        }
    }

    public boolean hasAnyTerminalCondition() {

        for (int i = 0; i < joinConditions.length; i++) {
            if (joinConditions[0].terminalCondition != null) {
                return true;
            }
        }

        for (int i = 0; i < whereConditions.length; i++) {
            if (whereConditions[0].terminalCondition != null) {
                return true;
            }
        }

        return false;
    }

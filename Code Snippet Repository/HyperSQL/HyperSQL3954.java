    private void setHandlers() {

        if (handlers.length == 0) {
            return;
        }

        HashSet           statesSet = new HashSet();
        OrderedIntHashSet typesSet  = new OrderedIntHashSet();

        for (int i = 0; i < handlers.length; i++) {
            int[] types = handlers[i].getConditionTypes();

            for (int j = 0; j < types.length; j++) {
                if (!typesSet.add(types[j])) {
                    throw Error.error(ErrorCode.X_42601);
                }
            }

            String[] states = handlers[i].getConditionStates();

            for (int j = 0; j < states.length; j++) {
                if (!statesSet.add(states[j])) {
                    throw Error.error(ErrorCode.X_42601);
                }
            }
        }
    }

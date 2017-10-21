    void logSequences() {

        HashMap map = sessionData.sequenceUpdateMap;

        if (map == null || map.isEmpty()) {
            return;
        }

        Iterator it = map.keySet().iterator();

        for (int i = 0, size = map.size(); i < size; i++) {
            NumberSequence sequence = (NumberSequence) it.next();

            database.logger.writeSequenceStatement(this, sequence);
        }

        sessionData.sequenceUpdateMap.clear();
    }

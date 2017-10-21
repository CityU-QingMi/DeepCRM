    void release() {

        for (int i = 0; i < tableList.size(); i++) {
            Table table = (Table) tableList.get(i);

            table.terminateTriggers();
        }

        triggerLookup         = null;
        indexLookup           = null;
        constraintLookup      = null;
        charsetLookup         = null;
        collationLookup       = null;
        procedureLookup       = null;
        functionLookup        = null;
        specificRoutineLookup = null;
        sequenceLookup        = null;
        tableLookup           = null;
        typeLookup            = null;

        tableList.clear();
        sequenceList.clear();
        referenceList.clear();
    }

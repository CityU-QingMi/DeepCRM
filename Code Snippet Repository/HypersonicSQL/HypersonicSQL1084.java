    void fireTriggers(Session session, int trigVecIndex,
                      RowSetNavigator rowSet) {

        if (!database.isReferentialIntegrity()) {
            return;
        }

        TriggerDef[] trigVec = triggerLists[trigVecIndex];

        for (int i = 0, size = trigVec.length; i < size; i++) {
            TriggerDef td         = trigVec[i];
            boolean    sqlTrigger = td instanceof TriggerDefSQL;

            if (td.hasOldTable()) {

                //
            }

            td.pushPair(session, null, null);
        }
    }

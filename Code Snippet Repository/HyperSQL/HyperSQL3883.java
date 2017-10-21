    private void pop(boolean isRoutine) {

        session.sessionData.persistentStoreCollection.pop(isRoutine);

        rownum = ((Integer) stack.remove(stack.size() - 1)).intValue();
        currentMaxRows = ((Integer) stack.remove(stack.size() - 1)).intValue();
        isInRoutine          = (Boolean) stack.remove(stack.size() - 1);
        noSQL                = (Boolean) stack.remove(stack.size() - 1);
        isReadOnly           = (Boolean) stack.remove(stack.size() - 1);
        isAutoCommit         = (Boolean) stack.remove(stack.size() - 1);
        lastIdentity         = (Number) stack.remove(stack.size() - 1);
        savepointTimestamps  = (LongDeque) stack.remove(stack.size() - 1);
        savepoints           = (HashMappedList) stack.remove(stack.size() - 1);
        rangeIterators = (RangeIterator[]) stack.remove(stack.size() - 1);
        routineCursors       = (Result[]) stack.remove(stack.size() - 1);
        routineVariables     = (Object[]) stack.remove(stack.size() - 1);
        triggerArguments     = ((Object[][]) stack.remove(stack.size() - 1));
        routineArguments     = (Object[]) stack.remove(stack.size() - 1);
        dynamicArguments     = (Object[]) stack.remove(stack.size() - 1);
        diagnosticsVariables = (Object[]) stack.remove(stack.size() - 1);

        depth--;
    }

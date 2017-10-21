    private void push(boolean isRoutine) {

        if (depth > 256) {
            throw Error.error(ErrorCode.GENERAL_ERROR);
        }

        session.sessionData.persistentStoreCollection.push(isRoutine);

        if (stack == null) {
            stack = new HsqlArrayList(32, true);
        }

        stack.add(diagnosticsVariables);
        stack.add(dynamicArguments);
        stack.add(routineArguments);
        stack.add(triggerArguments);
        stack.add(routineVariables);
        stack.add(routineCursors);
        stack.add(rangeIterators);
        stack.add(savepoints);
        stack.add(savepointTimestamps);
        stack.add(lastIdentity);
        stack.add(isAutoCommit);
        stack.add(isReadOnly);
        stack.add(noSQL);
        stack.add(isInRoutine);
        stack.add(ValuePool.getInt(currentMaxRows));
        stack.add(ValuePool.getInt(rownum));

        diagnosticsVariables =
            new Object[ExpressionColumn.diagnosticsVariableTokens.length];
        rangeIterators      = new RangeIterator[8];
        savepoints          = new HashMappedList(4);
        savepointTimestamps = new LongDeque();
        isAutoCommit        = Boolean.FALSE;
        currentMaxRows      = 0;
        isInRoutine         = Boolean.valueOf(isRoutine);

        depth++;
    }

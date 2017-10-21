    public Set<String> pauseTriggerGroup(Connection conn,
            GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {

        try {

            getDelegate().updateTriggerGroupStateFromOtherStates(
                    conn, matcher, STATE_PAUSED, STATE_ACQUIRED,
                    STATE_WAITING, STATE_WAITING);

            getDelegate().updateTriggerGroupStateFromOtherState(
                    conn, matcher, STATE_PAUSED_BLOCKED, STATE_BLOCKED);

            List<String> groups = getDelegate().selectTriggerGroups(conn, matcher);
            
            // make sure to account for an exact group match for a group that doesn't yet exist
            StringMatcher.StringOperatorName operator = matcher.getCompareWithOperator();
            if (operator.equals(StringOperatorName.EQUALS) && !groups.contains(matcher.getCompareToValue())) {
              groups.add(matcher.getCompareToValue());
            }

            for (String group : groups) {
                if (!getDelegate().isTriggerGroupPaused(conn, group)) {
                    getDelegate().insertPausedTriggerGroup(conn, group);
                }
            }

            return new HashSet<String>(groups);

        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't pause trigger group '"
                    + matcher + "': " + e.getMessage(), e);
        }
    }

    public void resetAlteredRoutineSettings() {

        if (isPSM()) {
            methodName               = null;
            javaMethod               = null;
            javaMethodWithConnection = false;
            parameterStyle           = PARAM_STYLE_SQL;

            if (dataImpact == NO_SQL) {
                dataImpact = CONTAINS_SQL;
            }
        } else {
            statement     = null;
            references    = null;
            variableCount = 0;
            cursorCount   = 0;
            ranges        = RangeVariable.emptyArray;
        }
    }

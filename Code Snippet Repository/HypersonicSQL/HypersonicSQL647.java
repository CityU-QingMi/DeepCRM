    public void setAsAlteredRoutine(Routine routine) {

        language          = routine.language;
        dataImpact        = routine.dataImpact;
        parameterStyle    = routine.parameterStyle;
        isDeterministic   = routine.isDeterministic;
        isNullInputOutput = routine.isNullInputOutput;
        maxDynamicResults = routine.maxDynamicResults;
        isRecursive       = routine.isRecursive;
        javaMethod        = routine.javaMethod;

        //
        isRecursive              = routine.isRecursive;
        javaMethodWithConnection = routine.javaMethodWithConnection;
        methodName               = routine.methodName;
        statement                = routine.statement;
        references               = routine.references;
        variableCount            = routine.variableCount;
        cursorCount              = routine.cursorCount;
        ranges                   = routine.ranges;
    }

    void reset(Session session, String sql) {

        scanner.reset(session, sql);

        //
        parsePosition             = 0;
        lastError                 = null;
        lastSynonym               = null;
        isCheckOrTriggerCondition = false;
        isSchemaDefinition        = false;
        isViewDefinition          = false;
        isRecording               = false;
        recordedStatement         = null;
    }

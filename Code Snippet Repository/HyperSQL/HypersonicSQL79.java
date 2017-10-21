    void clearStructures() {

        if (schemaManager != null) {
            schemaManager.release();
        }

        if (checkpointRunner != null) {
            checkpointRunner.stop();
        }

        if (timeoutRunner != null) {
            timeoutRunner.stop();
        }

        lobManager       = null;
        granteeManager   = null;
        userManager      = null;
        nameManager      = null;
        schemaManager    = null;
        sessionManager   = null;
        dbInfo           = null;
        checkpointRunner = null;
        timeoutRunner    = null;
    }

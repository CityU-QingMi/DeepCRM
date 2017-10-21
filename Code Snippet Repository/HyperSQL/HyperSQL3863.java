        public void run() {

            Statement checkpoint =
                ParserCommand.getAutoCheckpointStatement(Database.this);
            Session sysSession = sessionManager.newSysSession();

            try {
                sysSession.executeCompiledStatement(checkpoint,
                                                    ValuePool.emptyObjectArray,
                                                    0);
            } catch (Throwable e) {

                // ignore exceptions
                // may be InterruptedException or IOException
            } finally {
                sysSession.commit(false);
                sysSession.close();

                waiting = false;
            }
        }

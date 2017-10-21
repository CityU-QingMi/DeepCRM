    protected void executeCurrentSQL() {

        if (txtCommand.getText().length() < 1) {
            CommonSwing.errorMessage("No SQL to execute");

            return;
        }

        backgroundIt(new StatementExecRunnable(), "Executing SQL");
    }

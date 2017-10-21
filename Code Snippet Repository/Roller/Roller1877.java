    public void runScript(
            Connection con, boolean stopOnError) throws SQLException {
        failed = false;
        errors = false;
        for (String command : commands) {
            
            // run each command
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate(command);
                if (!con.getAutoCommit()) {
                    con.commit();
                }
                
                // on success, echo command to messages
                successMessage(command);
                
            } catch (SQLException ex) {
                if (command.contains("drop foreign key") || command.contains("drop index")) {
                    errorMessage("INFO: SQL command [" + command + "] failed, ignored.");
                    continue;
                }
                // add error message with text of SQL command to messages
                errorMessage("ERROR: SQLException executing SQL [" + command 
                        + "] : " + ex.getLocalizedMessage());
                // add stack trace to messages
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                errorMessage(sw.toString());
                if (stopOnError) {
                    failed = true;
                    throw ex;
                }
            }
        }
    }

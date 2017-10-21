        public void run() {
            try {
                TestUtil.testScript(conn, getName(), reader);
                success = true;
            } catch (TestUtil.TestRuntimeException tre) {
                System.err.println("Script '" + getName() + "' failed");
            } catch (IOException ioe) {
                System.err.println("Aborting thread for script '" + getName()
                        + "' due to: " + ioe);
                throw new RuntimeException(ioe);
            } catch (SQLException se) {
                System.err.println("Aborting thread for script '" + getName()
                        + "' due to: " + se);
                throw new RuntimeException(se);
            } finally { try {
                conn.close();
            } catch (SQLException se) {
                System.err.println("Failed to close JDBC connection for '"
                        + getName() + "': " + se);
            } }
        }

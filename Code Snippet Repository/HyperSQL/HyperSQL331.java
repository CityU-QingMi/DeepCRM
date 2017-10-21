    private static String promptForPassword(String username)
    throws PrivateException {

        BufferedReader console;
        String         password;

        password = null;

        try {
            console = new BufferedReader(new InputStreamReader(System.in));

            // Prompt for password
            System.out.print(SqltoolRB.passwordFor_prompt.getString(
                    RCData.expandSysPropVars(username)));

            // Read the password from the command line
            password = console.readLine();

            if (password == null) {
                password = "";
            } else {
                password = password.trim();
            }
        } catch (IOException e) {
            throw new PrivateException(e.getMessage());
        } finally {
            console = null;  // Encourage GC of buffers
        }

        return password;
    }

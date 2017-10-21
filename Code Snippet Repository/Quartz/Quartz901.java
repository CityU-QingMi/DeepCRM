    @Override
    public void login(String username, String password)
            throws LoginFailedException {
        System.out.println("UsernamePasswordValidator: login username '"
                + username + "' password '" + password + "'");
        try {
            assertThat(username, equalTo("realusername"));
            assertThat(password, equalTo("realpassword"));
        } catch (Throwable e) {
            error = new LoginFailedException(e.getMessage());
            throw error;
        }
    }

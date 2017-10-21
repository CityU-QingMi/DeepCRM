    public String authenticateBASIC(HttpServletRequest request) {
        boolean valid = false;
        String userID = null;
        String password = null;
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                StringTokenizer st = new StringTokenizer(authHeader);
                if (st.hasMoreTokens()) {
                    String basic = st.nextToken();
                    if (basic.equalsIgnoreCase("Basic")) {
                        String credentials = st.nextToken();
                        String userPass = new String(Base64.decodeBase64(credentials.getBytes()));
                        int p = userPass.indexOf(':');
                        if (p != -1) {
                            userID = userPass.substring(0, p);
                            User inUser = roller.getUserManager().getUserByUserName(userID);
                            boolean enabled = inUser.getEnabled();
                            if (enabled) {
                                // are passwords encrypted?
                                String encrypted =
                                        WebloggerConfig.getProperty("passwds.encryption.enabled");
                                password = userPass.substring(p+1);
                                if ("true".equalsIgnoreCase(encrypted)) {
                                    password = Utilities.encodePassword(password,
                                            WebloggerConfig.getProperty("passwds.encryption.algorithm"));
                                }
                                valid = inUser.getPassword().equals(password);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.debug("Error authenticating via BASIC Auth", e);
        }
        if (valid) {
            return userID;
        }
        return null;
    }

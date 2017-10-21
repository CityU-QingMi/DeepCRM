    public static void setHomeDir() {

        if (homedir == null) {
            try {
                Class c =
                    Class.forName("sun.security.action.GetPropertyAction");
                Constructor constructor = c.getConstructor(new Class[]{
                    String.class });
                java.security.PrivilegedAction a =
                    (java.security.PrivilegedAction) constructor.newInstance(
                        new Object[]{ "user.home" });

                homedir =
                    (String) java.security.AccessController.doPrivileged(a);
            } catch (Exception e) {
                System.err.println(
                    "No access to home directory.  Continuing without...");
            }
        }
    }

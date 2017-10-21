    public static void main(String[] arg) {

        System.getProperties().put("sun.java2d.noddraw", "true");

        bMustExit = true;

        try {
            work(arg);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(
                    "Try:  java "+ Transfer.class.getName() + " --help");
        }
    }

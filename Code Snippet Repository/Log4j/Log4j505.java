    public static void main(final String... args) throws Exception {
        final File file = new File(args[0]);
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            final Object result = in.readObject();
            System.out.println(result);
        } catch (final Throwable t) {
            System.err.println("Could not deserialize.");
            throw t; // cause non-zero exit code
        } finally {
            try {
                in.close();
            } catch (final Throwable t) {
                System.err.println("Error while closing: " + t);
            }
        }
    }

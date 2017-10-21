    public static void main(String[] arg)
    {
        if (arg.length != 2)
        {
            System.err.println("Usage - java org.eclipse.util.UnixCrypt <key> <salt>");
            System.exit(1);
        }

        System.err.println("Crypt=" + crypt(arg[0], arg[1]));
    }

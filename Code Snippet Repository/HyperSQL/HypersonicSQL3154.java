    public static void main(String[] sa)
    throws AclFormatException, IOException {

        if (sa.length > 1) {
            throw new RuntimeException("Try: java -cp path/to/hsqldb.jar "
                                       + ServerAcl.class.getName()
                                       + " --help");
        }

        if (sa.length > 0 && sa[0].equals("--help")) {
            System.err.println("SYNTAX: java -cp path/to/hsqldb.jar "
                               + ServerAcl.class.getName()
                               + " [filepath.txt]");
            System.err.println("ACL file path defaults to 'acl.txt' in the "
                               + "current directory.");
            System.exit(0);
        }

        ServerAcl serverAcl = new ServerAcl(new File((sa.length == 0)
            ? "acl.txt"
            : sa[0]));

        serverAcl.setPrintWriter(new PrintWriter(System.out));
        System.out.println(serverAcl.toString());

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter hostnames or IP addresses to be tested "
                           + "(one per line).");

        String s;

        while ((s = br.readLine()) != null) {
            s = s.trim();

            if (s.length() < 1) {
                continue;
            }

            System.out.println(Boolean.toString(serverAcl.permitAccess(s)));
        }
    }

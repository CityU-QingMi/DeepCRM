    public static void main(String[] sa) {

        if (sa.length > 0 && sa[0].equals("--help")) {
            System.err.println(SYNTAX_MSG);
            System.exit(0);
        }

        ArrayList outList  = new ArrayList();
        int       curInArg = -1;

        try {
            while (++curInArg < sa.length) {
                if (sa[curInArg].length() < 1) {
                    if (outList.size() < 1) {
                        syntaxFailure();
                    }

                    invoke((String) outList.remove(0),
                           (String[]) outList.toArray(emptyStringArray));
                    outList.clear();
                } else {
                    outList.add(sa[curInArg]);
                }
            }

            if (outList.size() < 1) {
                syntaxFailure();
            }

            invoke((String) outList.remove(0),
                   (String[]) outList.toArray(emptyStringArray));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String[] split(String s, String separator) {

        HsqlArrayList list      = new HsqlArrayList();
        int           currindex = 0;

        for (boolean more = true; more; ) {
            int nextindex = s.indexOf(separator, currindex);

            if (nextindex == -1) {
                nextindex = s.length();
                more      = false;
            }

            list.add(s.substring(currindex, nextindex));

            currindex = nextindex + separator.length();
        }

        return (String[]) list.toArray(new String[list.size()]);
    }

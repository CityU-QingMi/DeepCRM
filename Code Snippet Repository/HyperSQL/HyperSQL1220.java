    public static String convertToString(HsqlArrayList list, int offset) {

        StringBuffer sb = new StringBuffer();

        for (int i = offset; i < list.size(); i++) {
            sb.append(list.get(i)).append(LS);
        }

        return sb.toString();
    }

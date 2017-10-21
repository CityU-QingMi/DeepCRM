    private List<String> fromCSV (String csv)
    {
        if (csv == null || "".equals(csv.trim()))
            return null;
        String[] atoms = StringUtil.csvSplit(csv);
        List<String> list = new ArrayList<String>();
        for (String a:atoms)
        {
            list.add(a.trim());
        }
        return list;
    }

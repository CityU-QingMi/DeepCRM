    public String toString() {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        print("tlibversion", tlibversion, out);
        print("jspversion", jspversion, out);
        print("shortname", shortname, out);
        print("urn", urn, out);
        print("info", info, out);
        print("uri", uri, out);
        print("tagLibraryValidator", "" + tagLibraryValidator, out);

        for (int i = 0; i < tags.length; i++)
            out.println(tags[i].toString());

        for (int i = 0; i < tagFiles.length; i++)
            out.println(tagFiles[i].toString());

        for (int i = 0; i < functions.length; i++)
            out.println(functions[i].toString());

        return sw.toString();
    }

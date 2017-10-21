    private void genPreambleImports() throws JasperException {
        Iterator iter = pageInfo.getImports().iterator();
        while (iter.hasNext()) {
            out.printin("import ");
            out.print((String) iter.next());
            out.println(";");
        }

        out.println();
    }

    protected DisplaySection(HsqlArrayList sectionLines) {

/**/
/**/
        lines = (String[]) sectionLines.toArray();

        int firstSlash = lines[0].indexOf('/');

        lines[0] = lines[0].substring(firstSlash + 1).trim();
    }

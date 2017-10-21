    boolean matchesETag(String tagName) throws JasperException {
        Mark mark = mark();

        if (!matches("</" + tagName))
            return false;
        skipSpaces();
        if (nextChar() == '>')
            return true;

        reset(mark);
        return false;
    }

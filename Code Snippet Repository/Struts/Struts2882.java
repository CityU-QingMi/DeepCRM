    public String getURI(String prefix) {

        String uri = null;

        LinkedList stack = (LinkedList) xmlPrefixMapper.get(prefix);
        if (stack == null || stack.size() == 0) {
            uri = (String) jspPrefixMapper.get(prefix);
        } else {
            uri = (String) stack.getFirst();
        }

        return uri;
    }

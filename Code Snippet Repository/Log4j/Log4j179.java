    @Override
    public String pop() {
        checkInvariants();
        if (list.isEmpty()) {
            return null;
        }
        final int last = list.size() - 1;
        final String result = list.remove(last);
        return result;
    }

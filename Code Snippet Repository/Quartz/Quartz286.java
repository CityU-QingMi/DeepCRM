    private String join(Set<Integer> days, String sep) {
        StringBuilder sb = new StringBuilder();
        if (days == null || days.size() <= 0)
            return "";
        
        Iterator<Integer> itr = days.iterator();
        sb.append(itr.next());
        while(itr.hasNext()) {
            sb.append(sep).append(itr.next());
        }
        return sb.toString();
    }

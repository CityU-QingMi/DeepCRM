    protected String toSqlLikeClause(final GroupMatcher<?> matcher) {
        String groupName;
        switch(matcher.getCompareWithOperator()) {
            case EQUALS:
                groupName = matcher.getCompareToValue();
                break;
            case CONTAINS:
                groupName = "%" + matcher.getCompareToValue() + "%";
                break;
            case ENDS_WITH:
                groupName = "%" + matcher.getCompareToValue();
                break;
            case STARTS_WITH:
                groupName = matcher.getCompareToValue() + "%";
                break;
            case ANYTHING:
                groupName = "%";
                break;
            default:
                throw new UnsupportedOperationException("Don't know how to translate " + matcher.getCompareWithOperator() + " into SQL");
        }
        return groupName;
    }

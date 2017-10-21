    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append('@').append(Integer.toHexString(hashCode()));
        sb.append("[defaultPolicy=").append(defaultPolicy);
        sb.append(",creator=").append(creator.getClass().getName());
        sb.append("]");
        return sb.toString();
    }

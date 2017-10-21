    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (RollerMessage msg : mMessages) {
            sb.append(msg.getKey());
            sb.append(" : ");
        }
        for (RollerMessage msg : mErrors) {
            sb.append(msg.getKey());
            sb.append(" : ");
        }
        return sb.toString();
    }

    private void removePingTargetContents(PingTarget ping) 
            throws WebloggerException {
        // Remove the website's ping queue entries
        Query q = strategy.getNamedUpdate("PingQueueEntry.removeByPingTarget");
        q.setParameter(1, ping);
        q.executeUpdate();
        
        // Remove the website's auto ping configurations
        q = strategy.getNamedUpdate("AutoPing.removeByPingTarget");
        q.setParameter(1, ping);
        q.executeUpdate();
    }
